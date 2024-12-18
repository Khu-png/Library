-- Create Table

CREATE TABLE Category
(
    CategoryID VARCHAR(10) NOT NULL PRIMARY KEY,
    CategoryName NVARCHAR(50) NOT NULL
);

CREATE TABLE Author
(
    AuthorID VARCHAR(10) NOT NULL PRIMARY KEY,
    AuthorName NVARCHAR(50) NOT NULL,
    Notes NVARCHAR(255) NULL
);

CREATE TABLE Publisher
(
    PublisherID VARCHAR(10) NOT NULL PRIMARY KEY,
    PublisherName NVARCHAR(50) NOT NULL,
    Address NVARCHAR(50) NOT NULL,
    Email NVARCHAR(50) NOT NULL,
    PhoneNumber VARCHAR(15) NOT NULL
);

CREATE TABLE Account
(
    AccountID INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    Username NVARCHAR(50) NOT NULL UNIQUE,
    Password NVARCHAR(255) NOT NULL,
    Role NVARCHAR(20) NOT NULL
);

CREATE TABLE Reader
(
    ReaderID VARCHAR(10) NOT NULL PRIMARY KEY,
    ReaderName NVARCHAR(50) NOT NULL,
    Address NVARCHAR(50) NOT NULL,
    PhoneNumber VARCHAR(15) NOT NULL,
    LibraryCardID VARCHAR(10) NOT NULL
);

CREATE TABLE Staff
(
    StaffID VARCHAR(10) NOT NULL PRIMARY KEY,
    StaffName NVARCHAR(50) NOT NULL,
    BirthDate DATE NOT NULL,
    PhoneNumber VARCHAR(15) NOT NULL,
    Username NVARCHAR(50) NOT NULL
);

CREATE TABLE LibraryCard
(
    CardID VARCHAR(10) NOT NULL PRIMARY KEY,
    StartDate DATE NOT NULL,
    ExpiryDate DATE NOT NULL
);

CREATE TABLE Loan
(
    LoanID INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    CardID VARCHAR(10) NOT NULL,
    StaffID VARCHAR(10) NOT NULL,
    LoanDate DATE NOT NULL
);

CREATE TABLE Book
(
    BookID VARCHAR(10) NOT NULL PRIMARY KEY,
    BookName NVARCHAR(255) NOT NULL,
	Quantity INT NOT NULL,
    CategoryID VARCHAR(10) NOT NULL,
    AuthorID VARCHAR(10) NOT NULL,
    PublisherID VARCHAR(10) NOT NULL,
    PublishedYear INT NOT NULL
);

CREATE TABLE LoanDetail
(
    LoanDetailID INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    LoanID INT NOT NULL,
    BookID VARCHAR(10) NOT NULL,
    DueDate DATE NULL,
    ReturnDate DATE NULL,
    isdamaged Boolean NULL
);

-- Thêm FOREIGN KEY và CONSTRAINTS
ALTER TABLE Book
ADD FOREIGN KEY (CategoryID) REFERENCES Category(CategoryID),
    FOREIGN KEY (AuthorID) REFERENCES Author(AuthorID),
    FOREIGN KEY (PublisherID) REFERENCES Publisher(PublisherID);

ALTER TABLE Reader
ADD FOREIGN KEY (LibraryCardID) REFERENCES LibraryCard(CardID);

ALTER TABLE Staff
ADD FOREIGN KEY (Username) REFERENCES Account(Username);

ALTER TABLE Loan
ADD FOREIGN KEY (CardID) REFERENCES LibraryCard(CardID),
    FOREIGN KEY (StaffID) REFERENCES Staff(StaffID);

ALTER TABLE LoanDetail
ADD FOREIGN KEY (LoanID) REFERENCES Loan(LoanID),
    FOREIGN KEY (BookID) REFERENCES Book(BookID);
