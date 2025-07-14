package com.Onlineshop.app;

import java.util.*;
import java.io.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import com.Onlineshop.app.HibernateUtil;

class AutoSaveThread extends Thread {
    public void run() {
        try {
            while (true) {
                Thread.sleep(10 * 60 * 1000); // 10 minutes
                System.out.println("[AutoSaveThread] 🖘️ Autosave triggered...");
                // Add backup logic if needed
            }
        } catch (InterruptedException e) {
            System.out.println("[AutoSaveThread] Interrupted.");
        }
    }
}

public class Onlineshoppingsystem {

	static {

	    try {

	        System.setProperty("java.util.logging.config.file",

	        		Onlineshoppingsystem.class.getClassLoader().getResource("logging.properties").getPath());

	    } catch (Exception e) {

	        System.out.println("Logging config failed: " + e);

	    }

	}
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        new AutoSaveThread().start();

        while (true) {
            System.out.println("\n===== ONLINE SHOPPING SYSTEM =====");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Place Order");
            System.out.println("4. View Orders");
            System.out.println("5. Export Orders to File");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();

            switch (ch) {
                case 1: addProduct(); break;
                case 2: viewProducts(); break;
                case 3: placeOrder(); break;
                case 4: viewOrders(); break;
                case 5: exportOrdersToFile(); break;
                case 6:
                    System.out.println("Exiting... Thank you!");
                    HibernateUtil.shutdown();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void addProduct() {
        System.out.print("Enter product name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();

        String sql = "INSERT INTO products (name, price, quantity) VALUES (:name, :price, :qty)";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Query<?> query = session.createNativeQuery(sql);
            query.setParameter("name", name);
            query.setParameter("price", price);
            query.setParameter("qty", qty);
            query.executeUpdate();
            tx.commit();
            System.out.println("✅ Product added!");
        } catch (Exception e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    public static void viewProducts() {
        String sql = "SELECT * FROM products ORDER BY id";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            NativeQuery<Object[]> query = session.createNativeQuery(sql, Object[].class);
            List<Object[]> products = query.getResultList();

            System.out.println("----- Product List -----");
            System.out.printf("%-5s | %-20s | %-8s | %-8s%n", "ID", "NAME", "PRICE", "QTY");
            System.out.println("----------------------------------------------");

            for (Object[] row : products) {
                int id = ((Number) row[0]).intValue();
                String name = (String) row[1];
                double price = ((Number) row[2]).doubleValue();
                int qty = ((Number) row[3]).intValue();

                System.out.printf("%-5d | %-20s | %-8.2f | %-8d%n", id, name, price, qty);
            }

        } catch (Exception e) {
            System.out.println("Error viewing products: " + e.getMessage());
        }
    }

    public static void placeOrder() {
        System.out.print("Enter product ID to order: ");
        int pid = sc.nextInt();
        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Query<?> ps1 = session.createNativeQuery("SELECT quantity FROM products WHERE id = :id");
            ps1.setParameter("id", pid);
            List<?> result = ps1.getResultList();

            if (!result.isEmpty()) {
                int availableQty = ((Number) result.get(0)).intValue();

                Query<?> ps2 = session.createNativeQuery("INSERT INTO orders (product_id, quantity, order_date) VALUES (:pid, :qty, CURRENT_DATE)");
                ps2.setParameter("pid", pid);
                ps2.setParameter("qty", qty);
                ps2.executeUpdate();

                Query<?> ps3 = session.createNativeQuery("UPDATE products SET quantity = quantity - :qty WHERE id = :pid");
                ps3.setParameter("qty", qty);
                ps3.setParameter("pid", pid);
                ps3.executeUpdate();

                tx.commit();
                System.out.println("✅ Order placed!");
            } else {
                System.out.println("❌ Not enough stock or product not found.");
            }

        } catch (Exception e) {
            System.out.println("Error placing order: " + e.getMessage());
        }
    }

    public static void viewOrders() {
        String sql = "SELECT o.id, p.name, o.quantity, o.order_date FROM orders o JOIN products p ON o.product_id = p.id";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            NativeQuery<Object[]> query = session.createNativeQuery(sql, Object[].class);
            List<Object[]> orders = query.getResultList();

            System.out.println("----- Orders List -----");
            for (Object[] row : orders) {
                System.out.println("Order ID: " + row[0] +
                                   ", Product: " + row[1] +
                                   ", Qty: " + row[2] +
                                   ", Date: " + row[3]);
            }

        } catch (Exception e) {
            System.out.println("Error viewing orders: " + e.getMessage());
        }
    }

    public static void exportOrdersToFile() {
        String sql = "SELECT o.id, p.name, o.quantity, o.order_date FROM orders o JOIN products p ON o.product_id = p.id";

        try (Session session = HibernateUtil.getSessionFactory().openSession();
             PrintWriter pw = new PrintWriter(new FileWriter("orders.txt"))) {

            NativeQuery<Object[]> query = session.createNativeQuery(sql, Object[].class);
            List<Object[]> orders = query.getResultList();

            pw.println("OrderID | Product | Quantity | Date");
            pw.println("--------------------------------------------");

            for (Object[] row : orders) {
                pw.println(row[0] + " | " + row[1] + " | " + row[2] + " | " + row[3]);
            }

            System.out.println("✅ Orders exported to 'orders.txt'");

        } catch (Exception e) {
            System.out.println("Error exporting orders: " + e.getMessage());
        }
    }
}

