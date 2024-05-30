# Spring Boot Application

## Overview

This project is a Spring Boot application that provides [brief description of what the application does].

## Features

- This tool does CRUD operation on saving, state, county, department and user
- can send email to user in that department
- if a user already exists in the department, the email id is shown if not we can type in the email 

## Prerequisites

- Java 17 or higher
- Maven 3.9.6 or higher
- MySQL or any other database (optional, if the application uses a database)

## Getting Started

### Clone the repository

```bash
git clone https://github.com/geethaprema/crm-new.git
cd crm-new

- set below environment variables in nano ~/.zshrc
export SMTP_PWD=<gmailPassword>
export SMTP_USRNME=<gmailAddress>
export DB_URL=<db_URL>
export DB_USERNAME=<db_username>
export DB_PASSWORD=<db_password>

apply changes by sourcing the file: source ~/.zshrc 

### Build the package

mvn clean package

### Server Deployment

- [Server Deployment](/ServerREADME.md)
