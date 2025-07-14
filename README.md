# Online-Shopping-Hibernate
Console based Online Shopping Project using Hibernate Native SQL and JPA
# 🛒 Online Shopping System (Hibernate + PostgreSQL)

A **console-based online shopping system** built using **Hibernate 6.4.4**, **PostgreSQL**, and **Java 17**.  
This project uses **Hibernate Native SQL** and is **menu-driven**, includes **multithreading (AutoSave)** and supports **file I/O** operations like exporting order data.

---

## 📦 Features

- ✅ Add Products
- ✅ View All Products
- ✅ Place Orders
- ✅ View Orders with Date/Time
- ✅ Export Orders to `orders.txt`
- ✅ Background AutoSave thread every 10 minutes
- ✅ File-based import/export with CSV support *(optional)*

---

## 🧰 Tech Stack

| Layer            | Technology Used              |
|------------------|------------------------------|
| Language         | Java 17                      |
| ORM              | Hibernate 6.4.4 (Native SQL) |
| Persistence API  | Jakarta Persistence 3.1      |
| Database         | PostgreSQL                   |
| Build Tool       | Maven                        |
| IDE              | Eclipse                      |

---

## 📂 Project Structure
OnlineShoppingHibernate/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── Onlineshop/
│       │           ├── app/
│       │           │   └── Onlineshoppingsystem.java
│       │           ├── entity/
│       │           │   ├── Product.java
│       │           │   └── Order.java
│       │           └── util/
│       │               └── HibernateUtil.java
│       └── resources/
│           └── hibernate.cfg.xml
├── orders.txt                # Exported order data
├── pom.xml                   # Maven configuration
└── README.md                 # Project documentation

---

## 🛠️ Prerequisites

Before running the project, ensure you have:

- ✅ JDK 17 or above
- ✅ PostgreSQL installed and running on port `5432`
- ✅ Database named `javaproject` created
- ✅ Table schemas:
 
```sql
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DOUBLE PRECISION,
    quantity INT
);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    product_id INT REFERENCES products(id),
    quantity INT,
    order_time TIMESTAMP
);  
```
# 🧠 Notes
This project uses Hibernate Native SQL, not JPQL or Criteria.

Autosave thread prints a log every 10 minutes.

Orders are automatically timestamped using @PrePersist.

## ✍️ Developer

- **Tanishka Natraj Phand**  
  👩‍💻 Built with ❤️ in **Karmala, India**  
  📚 Hibernate + JPA + PostgreSQL + Java 17  

