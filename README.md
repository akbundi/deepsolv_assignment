# deepsolv_assignment
LinkedIn Insights Microservice

Overview

I have designed the LinkedIn Insights Microservice is a backend system designed to fetch, store, and retrieve insights from LinkedIn pages using web scraping. It provides an API to fetch LinkedIn page details, including basic information, posts, followers, and employees. Data is stored in a MySQL database, and the API follows RESTful principles.

Features

Mandatory Features:

Scraper Service: Extracts LinkedIn Page details including:

Page Name, URL, Profile Picture, Description, Industry, Total Followers, Head Count, Specialities, etc.

Recent 15-25 Posts along with comments.

Employees working at the company.

Persistent Storage: Uses MySQL to store scraped data with proper relationships.

RESTful API Endpoints:

Fetch LinkedIn Page details from the database (or scrape if not present).

Filter by follower count range, page name, industry.

Retrieve recent posts, followers, and employee details.

Pagination: Implemented for large datasets.

Postman Collection: Ready-to-use API collection.

Bonus Features that i have added:

Asynchronous Processing: Implements background tasks for scraping and storage.

Cloud Storage: Saves LinkedIn profile pictures and posts in AWS S3.

Caching: Implements caching with a 5-minute TTL for optimized performance.

Docker Support: Deployable with Docker.

***Technology Stack

Backend: Java, Spring Boot

Database: MySQL

Web Scraping: Jsoup

API Documentation: Postman Collection

Deployment:  AWS EC2, Docker
