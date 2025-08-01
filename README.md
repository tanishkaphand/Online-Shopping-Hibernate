Group Members:

1)Tanishka Phand

2)Shravani Devkar
# Online-Shopping-Hibernate

Console based Online Shopping Project using Hibernate Native SQL and JPA
# 🛒 Online Shopping System (Hibernate + PostgreSQL)

![Java](https://img.shields.io/badge/Java-17-blue)
![Maven](https://img.shields.io/badge/Maven-3.8.6-orange)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)
![JDBC](https://img.shields.io/badge/JDBC-4.2-green)
![Hibernate](https://img.shields.io/badge/Hibernate-7.0.5.Final-brightgreen)
![Platform](https://img.shields.io/badge/Platform-Console--Based-informational)

A **console-based online shopping system** built using **Hibernate 7.0.5**, **PostgreSQL**, and **Java 17**.  
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
| ORM              | Hibernate 7.0.5 (Native SQL) |
| Persistence API  | Jakarta Persistence 3.2      |
| Database         | PostgreSQL                   |
| Build Tool       | Maven                        |
| IDE              | Eclipse                      |

---

## 📂 Project Structure
```
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
```

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

# Project Screenshots
<img width="1818" height="762" alt="Screenshot (5)" src="https://github.com/user-attachments/assets/a2327390-79f5-488f-9247-41dcdde089b5" />
<img width="1870" height="729" alt="Screenshot (6)" src="https://github.com/user-attachments/assets/2999a9d6-1f2b-4a45-a7e2-16555465076f" />
<img width="1847" height="704" alt="Screenshot (7)" src="https://github.com/user-attachments/assets/b93603ea-1082-450f-b9f8-bd8601aec6c6" />
<img width="1848" height="574" alt="Screenshot (8)" src="https://github.com/user-attachments/assets/996184ae-3550-40ad-8bbd-9efcd4782dbd" />
<img width="1863" height="812" alt="Screenshot (9)" src="https://github.com/user-attachments/assets/35dfad70-71df-4e95-a735-5db539315862" />

## ✍️ Developer

- **Tanishka Natraj Phand**  
  👩‍💻 Built with ❤️ in **Karmala, India**  
  📚 Hibernate + JPA + PostgreSQL + Java 17  

