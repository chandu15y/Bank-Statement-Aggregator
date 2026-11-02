# Bank Statement Aggregator

## Project Overview

Bank Statement Aggregator is a Proof of Concept (POC) application developed using Java and Spring Boot. The application provides RESTful APIs to manage users, companies, branches, and bank statements across multiple banking entities.

The primary objective is to generate dummy bank statement records, upload the generated CSV files to Amazon S3, retrieve the files from cloud storage, convert CSV records into Java objects, and persist the transaction details in a relational database.

## Key Features

### 1. User Management and Authentication
- Provides REST APIs for user registration and login.
- Validates user credentials.
- Manages user information and company associations.

### 2. Company and Branch Management
- Provides APIs to create and manage companies.
- Supports multiple branches associated with a company.
- Maintains relationships between users, companies, and branches.

### 3. Bank Statement Generation
- Generates dummy bank statement records for different users and branches.
- Uses a predefined CSV format containing transaction details.
- Follows a structured file-naming convention.

### 4. AWS S3 Integration
- Integrates Amazon S3 for cloud-based bank statement storage.
- Uses `PutObjectRequest` to upload locally generated files to an S3 bucket.
- Uses `GetObjectRequest` to download statement files from S3.
- Organizes uploaded files based on user and company identifiers.

### 5. CSV Processing and Database Persistence
- Downloads bank statement files from AWS S3.
- Converts CSV records into Java objects.
- Stores the processed transaction information in the database.
- Maintains relationships between users, companies, branches, and transactions.

### 6. REST API Testing
- Implements test APIs to validate application functionality.
- Supports testing of user, company, branch, and bank statement operations.

## Application Workflow

1. Create a user account.
2. Authenticate the user through the login API.
3. Retrieve the associated company and branch information.
4. Generate dummy bank statement records.
5. Generate CSV files containing transaction details.
6. Upload the generated files to Amazon S3.
7. Download the files from S3 when required.
8. Convert CSV records into Java objects.
9. Persist the transaction details in the database.
10. Clean up temporary generated files to avoid unnecessary local storage consumption.

## Technology Stack

- **Programming Language:** Java
- **Backend Framework:** Spring Boot
- **ORM:** Hibernate
- **Persistence:** Spring Data JPA
- **Build Tool:** Maven
- **Cloud Storage:** Amazon AWS S3
- **File Processing:** CSV
- **API Architecture:** RESTful Web Services
- **Database:** Relational Database

## Project Architecture

The application follows a layered architecture:

- **Controllers:** Handle incoming REST API requests.
- **Models:** Represent entities such as User, Company, Branch, BankStatement, and Transaction.
- **Repositories:** Handle database operations.
- **Services:** Implement business logic and application workflows.
- **AWS Service:** Handles file uploads and downloads using Amazon S3.
- **Utilities:** Support common operations such as dummy data generation and file processing.

## Project Objective

This POC demonstrates the integration of Spring Boot REST APIs, relational database operations, CSV file processing, and AWS S3 cloud storage to build a centralized bank statement aggregation workflow.

**Note:** This project is developed for learning and POC purposes. Additional security, credential management, and production-readiness improvements would be required before real-world financial use.