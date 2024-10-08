USE [master]
GO
/****** Object:  Database [PhoneStore10]    Script Date: 8/8/2024 7:47:44 PM ******/
CREATE DATABASE [PhoneStore10]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PhoneStore10', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\PhoneStore10.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'PhoneStore10_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\PhoneStore10_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [PhoneStore10] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PhoneStore10].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PhoneStore10] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PhoneStore10] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PhoneStore10] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PhoneStore10] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PhoneStore10] SET ARITHABORT OFF 
GO
ALTER DATABASE [PhoneStore10] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [PhoneStore10] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PhoneStore10] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PhoneStore10] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PhoneStore10] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PhoneStore10] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PhoneStore10] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PhoneStore10] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PhoneStore10] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PhoneStore10] SET  ENABLE_BROKER 
GO
ALTER DATABASE [PhoneStore10] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PhoneStore10] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PhoneStore10] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PhoneStore10] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PhoneStore10] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PhoneStore10] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PhoneStore10] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PhoneStore10] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [PhoneStore10] SET  MULTI_USER 
GO
ALTER DATABASE [PhoneStore10] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PhoneStore10] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PhoneStore10] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PhoneStore10] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [PhoneStore10] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [PhoneStore10] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [PhoneStore10] SET QUERY_STORE = ON
GO
ALTER DATABASE [PhoneStore10] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [PhoneStore10]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 8/8/2024 7:47:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[AccountID] [int] IDENTITY(1,1) NOT NULL,
	[Username] [varchar](255) NOT NULL,
	[Password] [varchar](255) NOT NULL,
	[RoleID] [int] NOT NULL,
	[IsActive] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[AccountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Brand]    Script Date: 8/8/2024 7:47:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Brand](
	[BrandID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](500) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[BrandID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cart]    Script Date: 8/8/2024 7:47:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cart](
	[CartID] [int] IDENTITY(1,1) NOT NULL,
	[Quantity] [int] NOT NULL,
	[CustomerID] [int] NOT NULL,
	[ProductInfoID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[CartID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 8/8/2024 7:47:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[CategoryID] [int] IDENTITY(1,1) NOT NULL,
	[categoryName] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[CategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 8/8/2024 7:47:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[CustomerID] [int] IDENTITY(1,1) NOT NULL,
	[AccountID] [int] NOT NULL,
	[CustomerName] [nvarchar](255) NULL,
	[Age] [int] NULL,
	[Gender] [varchar](255) NULL,
	[Phone] [varchar](255) NULL,
	[DateOfBirth] [datetime2](6) NULL,
	[Address] [nvarchar](255) NULL,
	[Email] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[CustomerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Employee]    Script Date: 8/8/2024 7:47:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Employee](
	[EmployeeID] [int] IDENTITY(1,1) NOT NULL,
	[Phone] [nvarchar](15) NULL,
	[Email] [nvarchar](100) NULL,
	[FullName] [nvarchar](100) NOT NULL,
	[DOB] [date] NULL,
	[Gender] [nvarchar](10) NULL,
	[Address] [nvarchar](255) NULL,
	[AccountID] [int] NULL,
	[AgencyID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[EmployeeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Feedback]    Script Date: 8/8/2024 7:47:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Feedback](
	[FeedbackID] [int] IDENTITY(1,1) NOT NULL,
	[CustomerID] [int] NOT NULL,
	[ProductID] [int] NOT NULL,
	[Comment] [nvarchar](255) NULL,
	[Rating] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[FeedbackID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Image_Request]    Script Date: 8/8/2024 7:47:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Image_Request](
	[ImageID] [int] IDENTITY(1,1) NOT NULL,
	[ImageURL] [varchar](255) NULL,
	[Image_Name] [varchar](255) NULL,
	[RequestID] [int] NULL,
 CONSTRAINT [PK_Image_Request] PRIMARY KEY CLUSTERED 
(
	[ImageID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Invoice]    Script Date: 8/8/2024 7:47:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Invoice](
	[InvoiceID] [int] IDENTITY(1,1) NOT NULL,
	[CustomerID] [int] NOT NULL,
	[InvoiceType] [int] NULL,
	[InvoiceDate] [date] NULL,
	[Incurred] [decimal](10, 2) NULL,
	[IncurredDescription] [nvarchar](2000) NULL,
	[EmployeeID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[InvoiceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[InvoiceItem]    Script Date: 8/8/2024 7:47:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InvoiceItem](
	[InvoiceItemID] [int] IDENTITY(1,1) NOT NULL,
	[InvoiceID] [int] NOT NULL,
	[ProductInfoID] [int] NOT NULL,
	[Price] [decimal](10, 2) NOT NULL,
	[Quantity] [int] NOT NULL,
	[Discount] [numeric](5, 2) NULL,
	[ProducInfoID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[InvoiceItemID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 8/8/2024 7:47:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[ProductID] [int] IDENTITY(1,1) NOT NULL,
	[ProductName] [varchar](255) NULL,
	[ImageData] [varchar](255) NULL,
	[Comment] [nvarchar](255) NULL,
	[WarrantyPeriod] [datetime2](6) NULL,
	[CategoryID] [int] NULL,
	[Rating] [float] NULL,
	[BrandID] [int] NULL,
	[productStatus] [tinyint] NULL,
PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product_info]    Script Date: 8/8/2024 7:47:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product_info](
	[productInfoID] [int] IDENTITY(1,1) NOT NULL,
	[productID] [int] NOT NULL,
	[ProductInfoName] [nvarchar](255) NULL,
	[Price] [numeric](38, 2) NULL,
	[Description] [nvarchar](255) NULL,
	[Quantity] [int] NOT NULL,
	[ImportDate] [datetime2](6) NULL,
	[QuantitySold] [int] NULL,
	[Ram] [int] NULL,
	[Capacity] [int] NULL,
	[Color] [varchar](255) NULL,
	[productInfoStatus] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[productInfoID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductImage]    Script Date: 8/8/2024 7:47:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductImage](
	[ImageID] [int] IDENTITY(1,1) NOT NULL,
	[ProductInfoID] [int] NULL,
	[ImageURL] [varchar](255) NULL,
	[ProductID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ImageID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductStatus]    Script Date: 8/8/2024 7:47:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductStatus](
	[StatusID] [int] IDENTITY(1,1) NOT NULL,
	[StatusName] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[StatusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Request]    Script Date: 8/8/2024 7:47:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Request](
	[RequestID] [int] IDENTITY(1,1) NOT NULL,
	[CustomerID] [int] NOT NULL,
	[RequestType] [int] NOT NULL,
	[Description] [nvarchar](255) NULL,
	[EmployeeID] [int] NULL,
	[Answer] [nvarchar](255) NULL,
	[Date_created] [datetime] NULL,
	[Date_Answer] [datetime] NULL,
	[Title] [varchar](255) NULL,
	[InvoiceID] [int] NOT NULL,
	[Status] [int] NOT NULL,
 CONSTRAINT [PK_Request] PRIMARY KEY CLUSTERED 
(
	[RequestID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 8/8/2024 7:47:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[RoleID] [int] NOT NULL,
	[role_name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[RoleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([AccountID], [Username], [Password], [RoleID], [IsActive]) VALUES (1, N'admin123', N'$2a$10$DEQgOcuJBZBtTczV9f.mXeeoL1N0pAeIpCtjVOyNxej7a6JUE3iHy', 1, 1)
INSERT [dbo].[Account] ([AccountID], [Username], [Password], [RoleID], [IsActive]) VALUES (2, N'user123', N'$2a$10$BF3YQvMpLGzh3ORT7.eRHOyIftsbWt4tZeHqmpEJzr6fhZ3A5En1C', 2, 1)
INSERT [dbo].[Account] ([AccountID], [Username], [Password], [RoleID], [IsActive]) VALUES (3, N'user222', N'$2a$10$DEQgOcuJBZBtTczV9f.mXeeoL1N0pAeIpCtjVOyNxej7a6JUE3iHy', 2, 1)
INSERT [dbo].[Account] ([AccountID], [Username], [Password], [RoleID], [IsActive]) VALUES (4, N'user333', N'$2a$10$DEQgOcuJBZBtTczV9f.mXeeoL1N0pAeIpCtjVOyNxej7a6JUE3iHy', 2, 1)
INSERT [dbo].[Account] ([AccountID], [Username], [Password], [RoleID], [IsActive]) VALUES (5, N'user444', N'$2a$10$DEQgOcuJBZBtTczV9f.mXeeoL1N0pAeIpCtjVOyNxej7a6JUE3iHy', 2, 1)
INSERT [dbo].[Account] ([AccountID], [Username], [Password], [RoleID], [IsActive]) VALUES (6, N'user555', N'$2a$10$DEQgOcuJBZBtTczV9f.mXeeoL1N0pAeIpCtjVOyNxej7a6JUE3iHy', 2, 1)
INSERT [dbo].[Account] ([AccountID], [Username], [Password], [RoleID], [IsActive]) VALUES (7, N'employee1', N'$2a$10$DEQgOcuJBZBtTczV9f.mXeeoL1N0pAeIpCtjVOyNxej7a6JUE3iHy', 3, 1)
INSERT [dbo].[Account] ([AccountID], [Username], [Password], [RoleID], [IsActive]) VALUES (8, N'employee2', N'$2a$10$DEQgOcuJBZBtTczV9f.mXeeoL1N0pAeIpCtjVOyNxej7a6JUE3iHy', 3, 1)
INSERT [dbo].[Account] ([AccountID], [Username], [Password], [RoleID], [IsActive]) VALUES (9, N'employee3', N'$2a$10$DEQgOcuJBZBtTczV9f.mXeeoL1N0pAeIpCtjVOyNxej7a6JUE3iHy', 3, 1)
INSERT [dbo].[Account] ([AccountID], [Username], [Password], [RoleID], [IsActive]) VALUES (10, N'employee4', N'$2a$10$DEQgOcuJBZBtTczV9f.mXeeoL1N0pAeIpCtjVOyNxej7a6JUE3iHy', 3, 1)
INSERT [dbo].[Account] ([AccountID], [Username], [Password], [RoleID], [IsActive]) VALUES (12, N'user123456', N'$2a$10$Z2KKtKkI8G471florfpquO2Rt5eKspzhzQV08LcyWtpFF/jMzC0Je', 2, 1)
INSERT [dbo].[Account] ([AccountID], [Username], [Password], [RoleID], [IsActive]) VALUES (13, N'user12345678', N'$2a$10$/5z9QRxdJXJgg.fmgGa2e.NsCueGlb0rycmlXhGZvzFI81KGVqIdS', 2, 1)
INSERT [dbo].[Account] ([AccountID], [Username], [Password], [RoleID], [IsActive]) VALUES (14, N'hauvt11', N'$2a$10$.B46/h1gf8v8Q2m.mFKk2O.5UCj.2sbVfkmsxDeFZdkwn8psPPnqe', 2, 1)
SET IDENTITY_INSERT [dbo].[Account] OFF
GO
SET IDENTITY_INSERT [dbo].[Brand] ON 

INSERT [dbo].[Brand] ([BrandID], [Name]) VALUES (1, N'Iphone')
INSERT [dbo].[Brand] ([BrandID], [Name]) VALUES (2, N'SamSung')
INSERT [dbo].[Brand] ([BrandID], [Name]) VALUES (3, N'Xiaomi')
INSERT [dbo].[Brand] ([BrandID], [Name]) VALUES (4, N'OPPO')
INSERT [dbo].[Brand] ([BrandID], [Name]) VALUES (5, N'Vivo')
INSERT [dbo].[Brand] ([BrandID], [Name]) VALUES (6, N'TECNO')
INSERT [dbo].[Brand] ([BrandID], [Name]) VALUES (7, N'Nubia')
INSERT [dbo].[Brand] ([BrandID], [Name]) VALUES (8, N'Nubia')
INSERT [dbo].[Brand] ([BrandID], [Name]) VALUES (9, N'Sony')
INSERT [dbo].[Brand] ([BrandID], [Name]) VALUES (10, N'Masstel')
INSERT [dbo].[Brand] ([BrandID], [Name]) VALUES (14, N'Sony12')
INSERT [dbo].[Brand] ([BrandID], [Name]) VALUES (15, N'Sony13')
SET IDENTITY_INSERT [dbo].[Brand] OFF
GO
SET IDENTITY_INSERT [dbo].[Cart] ON 

INSERT [dbo].[Cart] ([CartID], [Quantity], [CustomerID], [ProductInfoID]) VALUES (1, 2, 1, 1)
INSERT [dbo].[Cart] ([CartID], [Quantity], [CustomerID], [ProductInfoID]) VALUES (2, 1, 2, 2)
INSERT [dbo].[Cart] ([CartID], [Quantity], [CustomerID], [ProductInfoID]) VALUES (3, 3, 3, 3)
INSERT [dbo].[Cart] ([CartID], [Quantity], [CustomerID], [ProductInfoID]) VALUES (4, 1, 4, 4)
INSERT [dbo].[Cart] ([CartID], [Quantity], [CustomerID], [ProductInfoID]) VALUES (5, 2, 5, 5)
SET IDENTITY_INSERT [dbo].[Cart] OFF
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([CategoryID], [categoryName]) VALUES (1, N'Điện thoại')
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[Customer] ON 

INSERT [dbo].[Customer] ([CustomerID], [AccountID], [CustomerName], [Age], [Gender], [Phone], [DateOfBirth], [Address], [Email]) VALUES (1, 2, N'John Doe', 35, N'Male', N'123-456-7890', CAST(N'1985-03-15T00:00:00.0000000' AS DateTime2), N'123 Main St, City', N'cuong2k31111@gmail.com')
INSERT [dbo].[Customer] ([CustomerID], [AccountID], [CustomerName], [Age], [Gender], [Phone], [DateOfBirth], [Address], [Email]) VALUES (2, 3, N'Alice Smith', 28, N'Female', N'0796322645', CAST(N'1992-07-10T00:00:00.0000000' AS DateTime2), N'456 Elm St, Town', N'hachiman1111200223@gmail.com')
INSERT [dbo].[Customer] ([CustomerID], [AccountID], [CustomerName], [Age], [Gender], [Phone], [DateOfBirth], [Address], [Email]) VALUES (3, 4, N'Bob Johnson', 42, N'Male', N'345-678-9012', CAST(N'1978-11-25T00:00:00.0000000' AS DateTime2), N'789 Oak St, Village', NULL)
INSERT [dbo].[Customer] ([CustomerID], [AccountID], [CustomerName], [Age], [Gender], [Phone], [DateOfBirth], [Address], [Email]) VALUES (4, 5, N'Emily Davis', 31, N'Female', N'456-789-0123', CAST(N'1989-09-05T00:00:00.0000000' AS DateTime2), N'890 Pine St, County', NULL)
INSERT [dbo].[Customer] ([CustomerID], [AccountID], [CustomerName], [Age], [Gender], [Phone], [DateOfBirth], [Address], [Email]) VALUES (5, 6, N'Mike Brown', 39, N'Male', N'567-890-1234', CAST(N'1981-01-20T00:00:00.0000000' AS DateTime2), N'567 Maple St, State', NULL)
INSERT [dbo].[Customer] ([CustomerID], [AccountID], [CustomerName], [Age], [Gender], [Phone], [DateOfBirth], [Address], [Email]) VALUES (6, 12, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Customer] ([CustomerID], [AccountID], [CustomerName], [Age], [Gender], [Phone], [DateOfBirth], [Address], [Email]) VALUES (7, 13, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Customer] ([CustomerID], [AccountID], [CustomerName], [Age], [Gender], [Phone], [DateOfBirth], [Address], [Email]) VALUES (8, 14, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[Customer] OFF
GO
SET IDENTITY_INSERT [dbo].[Employee] ON 

INSERT [dbo].[Employee] ([EmployeeID], [Phone], [Email], [FullName], [DOB], [Gender], [Address], [AccountID], [AgencyID]) VALUES (1, N'0796322645', N'employee1@example.com', N'Michael Smith', CAST(N'1980-05-20' AS Date), N'Male', N'555 Cedar St, Country', 7, 1)
INSERT [dbo].[Employee] ([EmployeeID], [Phone], [Email], [FullName], [DOB], [Gender], [Address], [AccountID], [AgencyID]) VALUES (2, N'666-666-6666', N'employee2@example.com', N'Sarah Johnson', CAST(N'1985-10-15' AS Date), N'Female', N'666 Birch St, Town', 8, 2)
INSERT [dbo].[Employee] ([EmployeeID], [Phone], [Email], [FullName], [DOB], [Gender], [Address], [AccountID], [AgencyID]) VALUES (3, N'777-777-7777', N'employee3@example.com', N'David Wilson', CAST(N'1975-03-30' AS Date), N'Male', N'777 Oak St, City', 9, 3)
INSERT [dbo].[Employee] ([EmployeeID], [Phone], [Email], [FullName], [DOB], [Gender], [Address], [AccountID], [AgencyID]) VALUES (4, N'888-888-8888', N'employee4@example.com', N'Jessica Garcia', CAST(N'1990-08-25' AS Date), N'Female', N'888 Elm St, Village', 10, 4)
SET IDENTITY_INSERT [dbo].[Employee] OFF
GO
SET IDENTITY_INSERT [dbo].[Image_Request] ON 

INSERT [dbo].[Image_Request] ([ImageID], [ImageURL], [Image_Name], [RequestID]) VALUES (1, N'/uploads/7271ff95-a07f-4906-8cd1-e574b4d8c394.jpg', N'image1.jpg', 1)
INSERT [dbo].[Image_Request] ([ImageID], [ImageURL], [Image_Name], [RequestID]) VALUES (2, N'/uploads/5fdca513-a89b-40df-8108-8a3ade3a6510.jpeg', N'oppoA58.jpeg', 2)
INSERT [dbo].[Image_Request] ([ImageID], [ImageURL], [Image_Name], [RequestID]) VALUES (3, N'/uploads/879de39d-aac4-4ad7-a3e6-43466594ba62.jpg', N'ps5.jpg', 2)
SET IDENTITY_INSERT [dbo].[Image_Request] OFF
GO
SET IDENTITY_INSERT [dbo].[Invoice] ON 

INSERT [dbo].[Invoice] ([InvoiceID], [CustomerID], [InvoiceType], [InvoiceDate], [Incurred], [IncurredDescription], [EmployeeID]) VALUES (1, 1, 1, CAST(N'2024-11-11' AS Date), CAST(1.00 AS Decimal(10, 2)), N'1', 1)
INSERT [dbo].[Invoice] ([InvoiceID], [CustomerID], [InvoiceType], [InvoiceDate], [Incurred], [IncurredDescription], [EmployeeID]) VALUES (2, 1, 1, CAST(N'2024-11-11' AS Date), CAST(1.00 AS Decimal(10, 2)), N'1', 1)
SET IDENTITY_INSERT [dbo].[Invoice] OFF
GO
SET IDENTITY_INSERT [dbo].[InvoiceItem] ON 

INSERT [dbo].[InvoiceItem] ([InvoiceItemID], [InvoiceID], [ProductInfoID], [Price], [Quantity], [Discount], [ProducInfoID]) VALUES (1, 1, 1, CAST(990.00 AS Decimal(10, 2)), 4, CAST(0.00 AS Numeric(5, 2)), 1)
INSERT [dbo].[InvoiceItem] ([InvoiceItemID], [InvoiceID], [ProductInfoID], [Price], [Quantity], [Discount], [ProducInfoID]) VALUES (5, 2, 1, CAST(990.00 AS Decimal(10, 2)), 3, CAST(0.00 AS Numeric(5, 2)), 1)
INSERT [dbo].[InvoiceItem] ([InvoiceItemID], [InvoiceID], [ProductInfoID], [Price], [Quantity], [Discount], [ProducInfoID]) VALUES (6, 2, 2, CAST(7990000.00 AS Decimal(10, 2)), 3, CAST(0.00 AS Numeric(5, 2)), 2)
SET IDENTITY_INSERT [dbo].[InvoiceItem] OFF
GO
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([ProductID], [ProductName], [ImageData], [Comment], [WarrantyPeriod], [CategoryID], [Rating], [BrandID], [productStatus]) VALUES (1, N'iPhone 9', N'iPhone 9z.jpg', N'Top selling iPhone model', CAST(N'2023-05-05T00:00:00.0000000' AS DateTime2), 1, 0, 6, 1)
INSERT [dbo].[Product] ([ProductID], [ProductName], [ImageData], [Comment], [WarrantyPeriod], [CategoryID], [Rating], [BrandID], [productStatus]) VALUES (2, N'Samsung Galaxy Tab S7', N'image2.jpg', N'Powerful Android tablet', CAST(N'2024-03-10T00:00:00.0000000' AS DateTime2), 1, 4.2, 1, 1)
INSERT [dbo].[Product] ([ProductID], [ProductName], [ImageData], [Comment], [WarrantyPeriod], [CategoryID], [Rating], [BrandID], [productStatus]) VALUES (3, N'Wireless Earbuds', N'image3.jpg', N'High-quality sound', CAST(N'2023-07-15T00:00:00.0000000' AS DateTime2), 1, 4, 1, 1)
INSERT [dbo].[Product] ([ProductID], [ProductName], [ImageData], [Comment], [WarrantyPeriod], [CategoryID], [Rating], [BrandID], [productStatus]) VALUES (4, N'MacBook Pro', N'image4.jpg', N'Ideal for professionals', CAST(N'2025-11-20T00:00:00.0000000' AS DateTime2), 1, 4.7, 1, 1)
INSERT [dbo].[Product] ([ProductID], [ProductName], [ImageData], [Comment], [WarrantyPeriod], [CategoryID], [Rating], [BrandID], [productStatus]) VALUES (5, N'Apple Watch Series 6', N'image5.jpg', N'Fitness tracking smartwatch', CAST(N'2023-09-30T00:00:00.0000000' AS DateTime2), 1, 4.3, 1, 1)
INSERT [dbo].[Product] ([ProductID], [ProductName], [ImageData], [Comment], [WarrantyPeriod], [CategoryID], [Rating], [BrandID], [productStatus]) VALUES (6, N'Canon EOS R5', N'image6.jpg', N'Professional mirrorless camera', CAST(N'2024-08-25T00:00:00.0000000' AS DateTime2), 1, 4.8, 1, 1)
INSERT [dbo].[Product] ([ProductID], [ProductName], [ImageData], [Comment], [WarrantyPeriod], [CategoryID], [Rating], [BrandID], [productStatus]) VALUES (7, N'PlayStation 5', N'image7.jpg', N'Next-gen gaming console', CAST(N'2023-12-15T00:00:00.0000000' AS DateTime2), 1, 4.6, 1, 1)
INSERT [dbo].[Product] ([ProductID], [ProductName], [ImageData], [Comment], [WarrantyPeriod], [CategoryID], [Rating], [BrandID], [productStatus]) VALUES (8, N'Sony WH-1000XM4', N'image8.jpg', N'Noise cancelling headphones', CAST(N'2023-04-01T00:00:00.0000000' AS DateTime2), 1, 4.5, 1, 1)
INSERT [dbo].[Product] ([ProductID], [ProductName], [ImageData], [Comment], [WarrantyPeriod], [CategoryID], [Rating], [BrandID], [productStatus]) VALUES (9, N'Bose SoundLink Revolve', N'image9.jpg', N'360-degree sound speaker', CAST(N'2023-06-10T00:00:00.0000000' AS DateTime2), 1, 4.4, 1, 1)
INSERT [dbo].[Product] ([ProductID], [ProductName], [ImageData], [Comment], [WarrantyPeriod], [CategoryID], [Rating], [BrandID], [productStatus]) VALUES (10, N'Anker PowerCore', N'image10.jpg', N'Portable charger for on-the-go', CAST(N'2023-08-20T00:00:00.0000000' AS DateTime2), 1, 4, 1, 1)
INSERT [dbo].[Product] ([ProductID], [ProductName], [ImageData], [Comment], [WarrantyPeriod], [CategoryID], [Rating], [BrandID], [productStatus]) VALUES (23, N'iPhone 9', N'iPhone 9.jpg', N'Top selling iPhone model', CAST(N'2024-08-08T16:52:44.2406530' AS DateTime2), 1, 0, 1, 1)
SET IDENTITY_INSERT [dbo].[Product] OFF
GO
SET IDENTITY_INSERT [dbo].[product_info] ON 

INSERT [dbo].[product_info] ([productInfoID], [productID], [ProductInfoName], [Price], [Description], [Quantity], [ImportDate], [QuantitySold], [Ram], [Capacity], [Color], [productInfoStatus]) VALUES (1, 1, N'Iphone 12 Xanh', CAST(999.00 AS Numeric(38, 2)), N'Flagship smartphone from Apple', 140, CAST(N'2021-01-20T00:00:00.0000000' AS DateTime2), 50, 12, 512, N'Xanh', 1)
INSERT [dbo].[product_info] ([productInfoID], [productID], [ProductInfoName], [Price], [Description], [Quantity], [ImportDate], [QuantitySold], [Ram], [Capacity], [Color], [productInfoStatus]) VALUES (2, 2, N'Samsung Galaxy Tab S7', CAST(7990000.00 AS Numeric(38, 2)), N'Premium Android tablet with S Pen', 50, CAST(N'2021-02-15T00:00:00.0000000' AS DateTime2), 25, 8, 256, N'Black', 1)
INSERT [dbo].[product_info] ([productInfoID], [productID], [ProductInfoName], [Price], [Description], [Quantity], [ImportDate], [QuantitySold], [Ram], [Capacity], [Color], [productInfoStatus]) VALUES (3, 3, N'Wireless Earbuds', CAST(1990000.00 AS Numeric(38, 2)), N'Wireless earbuds with active noise cancellation', 200, CAST(N'2021-03-10T00:00:00.0000000' AS DateTime2), 100, 0, 0, N'White', 1)
INSERT [dbo].[product_info] ([productInfoID], [productID], [ProductInfoName], [Price], [Description], [Quantity], [ImportDate], [QuantitySold], [Ram], [Capacity], [Color], [productInfoStatus]) VALUES (4, 4, N'MacBook Pro', CAST(1199000.00 AS Numeric(38, 2)), N'Thin and light laptop with Apple Silicon chip', 80, CAST(N'2021-04-05T00:00:00.0000000' AS DateTime2), 40, 16, 512, N'Silver', 1)
INSERT [dbo].[product_info] ([productInfoID], [productID], [ProductInfoName], [Price], [Description], [Quantity], [ImportDate], [QuantitySold], [Ram], [Capacity], [Color], [productInfoStatus]) VALUES (5, 5, N'Apple Watch Series 6', CAST(2790000.00 AS Numeric(38, 2)), N'Affordable smartwatch from Apple', 150, CAST(N'2021-05-01T00:00:00.0000000' AS DateTime2), 75, 0, 0, N'Black', 1)
INSERT [dbo].[product_info] ([productInfoID], [productID], [ProductInfoName], [Price], [Description], [Quantity], [ImportDate], [QuantitySold], [Ram], [Capacity], [Color], [productInfoStatus]) VALUES (6, 6, N'Canon EOS R5', CAST(2499000.00 AS Numeric(38, 2)), N'Full-frame mirrorless camera with 4K video', 30, CAST(N'2021-06-20T00:00:00.0000000' AS DateTime2), 15, 0, 0, N'Black', 1)
INSERT [dbo].[product_info] ([productInfoID], [productID], [ProductInfoName], [Price], [Description], [Quantity], [ImportDate], [QuantitySold], [Ram], [Capacity], [Color], [productInfoStatus]) VALUES (7, 7, N'PlayStation 5', CAST(49900000.00 AS Numeric(38, 2)), N'Next-gen gaming console with powerful specs', 100, CAST(N'2021-07-15T00:00:00.0000000' AS DateTime2), 50, 0, 0, N'Black', 1)
INSERT [dbo].[product_info] ([productInfoID], [productID], [ProductInfoName], [Price], [Description], [Quantity], [ImportDate], [QuantitySold], [Ram], [Capacity], [Color], [productInfoStatus]) VALUES (8, 8, N'Sony WH-1000XM4', CAST(3490000.00 AS Numeric(38, 2)), N'Premium Bluetooth headphones with noise cancellation', 120, CAST(N'2021-08-10T00:00:00.0000000' AS DateTime2), 60, 0, 0, N'Black', 1)
INSERT [dbo].[product_info] ([productInfoID], [productID], [ProductInfoName], [Price], [Description], [Quantity], [ImportDate], [QuantitySold], [Ram], [Capacity], [Color], [productInfoStatus]) VALUES (9, 9, N'Bose SoundLink Revolve', CAST(1190000.00 AS Numeric(38, 2)), N'Portable Bluetooth speaker with waterproof design', 180, CAST(N'2021-09-05T00:00:00.0000000' AS DateTime2), 90, 12, 128, N'Black', 1)
INSERT [dbo].[product_info] ([productInfoID], [productID], [ProductInfoName], [Price], [Description], [Quantity], [ImportDate], [QuantitySold], [Ram], [Capacity], [Color], [productInfoStatus]) VALUES (10, 10, N'Anker PowerCore', CAST(4900000.00 AS Numeric(38, 2)), N'High-capacity portable charger for smartphones', 250, CAST(N'2021-10-01T00:00:00.0000000' AS DateTime2), 125, 0, 0, N'Black', 1)
INSERT [dbo].[product_info] ([productInfoID], [productID], [ProductInfoName], [Price], [Description], [Quantity], [ImportDate], [QuantitySold], [Ram], [Capacity], [Color], [productInfoStatus]) VALUES (12, 1, N'Iphone 12 Đen', CAST(8500000.00 AS Numeric(38, 2)), N'Flagship smartphone from Apple', 120, CAST(N'2021-01-20T00:00:00.0000000' AS DateTime2), 50, 8, 256, N'Ðen', 1)
INSERT [dbo].[product_info] ([productInfoID], [productID], [ProductInfoName], [Price], [Description], [Quantity], [ImportDate], [QuantitySold], [Ram], [Capacity], [Color], [productInfoStatus]) VALUES (13, 1, N'Iphone 12 Vàng', CAST(8500000.00 AS Numeric(38, 2)), N'Flagship smartphone from Apple', 120, CAST(N'2021-01-20T00:00:00.0000000' AS DateTime2), 50, 8, 256, N'Vàng', 1)
INSERT [dbo].[product_info] ([productInfoID], [productID], [ProductInfoName], [Price], [Description], [Quantity], [ImportDate], [QuantitySold], [Ram], [Capacity], [Color], [productInfoStatus]) VALUES (14, 1, N'Iphone 12 Bạc', CAST(8500000.00 AS Numeric(38, 2)), N'Flagship smartphone from Apple', 50, CAST(N'2021-01-20T00:00:00.0000000' AS DateTime2), 50, 8, 256, N'B?c', 1)
INSERT [dbo].[product_info] ([productInfoID], [productID], [ProductInfoName], [Price], [Description], [Quantity], [ImportDate], [QuantitySold], [Ram], [Capacity], [Color], [productInfoStatus]) VALUES (15, 1, N'1', CAST(999.99 AS Numeric(38, 2)), N'Flagship smartphone from Apple', 100, CAST(N'2021-01-20T00:00:00.0000000' AS DateTime2), 50, 8, 256, N'Pacific Blue', 1)
INSERT [dbo].[product_info] ([productInfoID], [productID], [ProductInfoName], [Price], [Description], [Quantity], [ImportDate], [QuantitySold], [Ram], [Capacity], [Color], [productInfoStatus]) VALUES (27, 23, N'iPhone 9 16G Vang', CAST(1000000.00 AS Numeric(38, 2)), N'ddinhr', 1, CAST(N'2024-08-08T16:52:44.2896530' AS DateTime2), 0, 2, 16, N'Vang', 1)
SET IDENTITY_INSERT [dbo].[product_info] OFF
GO
SET IDENTITY_INSERT [dbo].[ProductImage] ON 

INSERT [dbo].[ProductImage] ([ImageID], [ProductInfoID], [ImageURL], [ProductID]) VALUES (1, 1, N'ip9.jfif', 1)
INSERT [dbo].[ProductImage] ([ImageID], [ProductInfoID], [ImageURL], [ProductID]) VALUES (2, 2, N'Tab7.jfif', 2)
INSERT [dbo].[ProductImage] ([ImageID], [ProductInfoID], [ImageURL], [ProductID]) VALUES (3, 3, N'wirlesearbud.jpg', 3)
INSERT [dbo].[ProductImage] ([ImageID], [ProductInfoID], [ImageURL], [ProductID]) VALUES (4, 4, N'Macpro.jpg', 4)
INSERT [dbo].[ProductImage] ([ImageID], [ProductInfoID], [ImageURL], [ProductID]) VALUES (5, 5, N'apple6.jpg', 5)
INSERT [dbo].[ProductImage] ([ImageID], [ProductInfoID], [ImageURL], [ProductID]) VALUES (6, 6, N'cannon.jpg', 6)
INSERT [dbo].[ProductImage] ([ImageID], [ProductInfoID], [ImageURL], [ProductID]) VALUES (7, 7, N'ps5.jpg', 7)
INSERT [dbo].[ProductImage] ([ImageID], [ProductInfoID], [ImageURL], [ProductID]) VALUES (8, 8, N'sony.jpg', 8)
INSERT [dbo].[ProductImage] ([ImageID], [ProductInfoID], [ImageURL], [ProductID]) VALUES (9, 9, N'bose.jpg', 9)
INSERT [dbo].[ProductImage] ([ImageID], [ProductInfoID], [ImageURL], [ProductID]) VALUES (10, 10, N'anker.jpg', 10)
INSERT [dbo].[ProductImage] ([ImageID], [ProductInfoID], [ImageURL], [ProductID]) VALUES (11, 12, N'ip9.jfif', 1)
INSERT [dbo].[ProductImage] ([ImageID], [ProductInfoID], [ImageURL], [ProductID]) VALUES (12, 13, N'ip9.jfif', 1)
INSERT [dbo].[ProductImage] ([ImageID], [ProductInfoID], [ImageURL], [ProductID]) VALUES (13, 14, N'ip9.jfif', 1)
INSERT [dbo].[ProductImage] ([ImageID], [ProductInfoID], [ImageURL], [ProductID]) VALUES (14, 15, N'ip9.jfif', 1)
INSERT [dbo].[ProductImage] ([ImageID], [ProductInfoID], [ImageURL], [ProductID]) VALUES (28, 27, N'27.jpg', 23)
INSERT [dbo].[ProductImage] ([ImageID], [ProductInfoID], [ImageURL], [ProductID]) VALUES (29, 27, N'271.jpg', 23)
SET IDENTITY_INSERT [dbo].[ProductImage] OFF
GO
SET IDENTITY_INSERT [dbo].[ProductStatus] ON 

INSERT [dbo].[ProductStatus] ([StatusID], [StatusName]) VALUES (1, N'Hoạt động')
INSERT [dbo].[ProductStatus] ([StatusID], [StatusName]) VALUES (2, N'Không hoạt động')
INSERT [dbo].[ProductStatus] ([StatusID], [StatusName]) VALUES (3, N'Hết hàng')
INSERT [dbo].[ProductStatus] ([StatusID], [StatusName]) VALUES (4, N'Pre-order')
INSERT [dbo].[ProductStatus] ([StatusID], [StatusName]) VALUES (5, N'Coming Soon')
SET IDENTITY_INSERT [dbo].[ProductStatus] OFF
GO
SET IDENTITY_INSERT [dbo].[Request] ON 

INSERT [dbo].[Request] ([RequestID], [CustomerID], [RequestType], [Description], [EmployeeID], [Answer], [Date_created], [Date_Answer], [Title], [InvoiceID], [Status]) VALUES (1, 1, 1, N'1', 1, N'OKe ngon', CAST(N'2024-08-08T09:44:35.007' AS DateTime), CAST(N'2024-08-08T09:45:06.013' AS DateTime), N'1', 1, 2)
INSERT [dbo].[Request] ([RequestID], [CustomerID], [RequestType], [Description], [EmployeeID], [Answer], [Date_created], [Date_Answer], [Title], [InvoiceID], [Status]) VALUES (2, 1, 2, N'1', 1, N'Không ổn vì giống tự làm hỏng', CAST(N'2024-08-08T14:05:39.273' AS DateTime), CAST(N'2024-08-08T14:08:29.247' AS DateTime), N'1', 2, 0)
SET IDENTITY_INSERT [dbo].[Request] OFF
GO
INSERT [dbo].[Role] ([RoleID], [role_name]) VALUES (1, N'admin')
INSERT [dbo].[Role] ([RoleID], [role_name]) VALUES (2, N'customer')
INSERT [dbo].[Role] ([RoleID], [role_name]) VALUES (3, N'employee')
GO
/****** Object:  Index [UK12080ci28ytixftor29e7ix5q]    Script Date: 8/8/2024 7:47:44 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK12080ci28ytixftor29e7ix5q] ON [dbo].[Customer]
(
	[AccountID] ASC
)
WHERE ([accountid] IS NOT NULL)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Account]  WITH CHECK ADD FOREIGN KEY([RoleID])
REFERENCES [dbo].[Role] ([RoleID])
GO
ALTER TABLE [dbo].[Account]  WITH CHECK ADD FOREIGN KEY([RoleID])
REFERENCES [dbo].[Role] ([RoleID])
GO
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK4uk2u5ju46nwjwvlwt1jol9ue] FOREIGN KEY([RoleID])
REFERENCES [dbo].[Role] ([RoleID])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK4uk2u5ju46nwjwvlwt1jol9ue]
GO
ALTER TABLE [dbo].[Cart]  WITH CHECK ADD FOREIGN KEY([CustomerID])
REFERENCES [dbo].[Customer] ([CustomerID])
GO
ALTER TABLE [dbo].[Cart]  WITH CHECK ADD FOREIGN KEY([CustomerID])
REFERENCES [dbo].[Customer] ([CustomerID])
GO
ALTER TABLE [dbo].[Cart]  WITH CHECK ADD FOREIGN KEY([ProductInfoID])
REFERENCES [dbo].[product_info] ([productInfoID])
GO
ALTER TABLE [dbo].[Cart]  WITH CHECK ADD FOREIGN KEY([ProductInfoID])
REFERENCES [dbo].[product_info] ([productInfoID])
GO
ALTER TABLE [dbo].[Customer]  WITH CHECK ADD FOREIGN KEY([AccountID])
REFERENCES [dbo].[Account] ([AccountID])
GO
ALTER TABLE [dbo].[Customer]  WITH CHECK ADD FOREIGN KEY([AccountID])
REFERENCES [dbo].[Account] ([AccountID])
GO
ALTER TABLE [dbo].[Customer]  WITH CHECK ADD  CONSTRAINT [FKass5qvmiyb6jsxaqjmk0mj4bw] FOREIGN KEY([AccountID])
REFERENCES [dbo].[Account] ([AccountID])
GO
ALTER TABLE [dbo].[Customer] CHECK CONSTRAINT [FKass5qvmiyb6jsxaqjmk0mj4bw]
GO
ALTER TABLE [dbo].[Employee]  WITH CHECK ADD FOREIGN KEY([AccountID])
REFERENCES [dbo].[Account] ([AccountID])
GO
ALTER TABLE [dbo].[Employee]  WITH CHECK ADD FOREIGN KEY([AccountID])
REFERENCES [dbo].[Account] ([AccountID])
GO
ALTER TABLE [dbo].[Feedback]  WITH CHECK ADD FOREIGN KEY([CustomerID])
REFERENCES [dbo].[Customer] ([CustomerID])
GO
ALTER TABLE [dbo].[Feedback]  WITH CHECK ADD FOREIGN KEY([CustomerID])
REFERENCES [dbo].[Customer] ([CustomerID])
GO
ALTER TABLE [dbo].[Feedback]  WITH CHECK ADD FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ProductID])
GO
ALTER TABLE [dbo].[Feedback]  WITH CHECK ADD FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ProductID])
GO
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD FOREIGN KEY([CustomerID])
REFERENCES [dbo].[Customer] ([CustomerID])
GO
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD  CONSTRAINT [FK_Invoice_Employee] FOREIGN KEY([EmployeeID])
REFERENCES [dbo].[Employee] ([EmployeeID])
GO
ALTER TABLE [dbo].[Invoice] CHECK CONSTRAINT [FK_Invoice_Employee]
GO
ALTER TABLE [dbo].[InvoiceItem]  WITH CHECK ADD FOREIGN KEY([InvoiceID])
REFERENCES [dbo].[Invoice] ([InvoiceID])
GO
ALTER TABLE [dbo].[InvoiceItem]  WITH CHECK ADD FOREIGN KEY([ProductInfoID])
REFERENCES [dbo].[product_info] ([productInfoID])
GO
ALTER TABLE [dbo].[InvoiceItem]  WITH CHECK ADD FOREIGN KEY([ProductInfoID])
REFERENCES [dbo].[product_info] ([productInfoID])
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD FOREIGN KEY([CategoryID])
REFERENCES [dbo].[Category] ([CategoryID])
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD FOREIGN KEY([CategoryID])
REFERENCES [dbo].[Category] ([CategoryID])
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Branch] FOREIGN KEY([BrandID])
REFERENCES [dbo].[Brand] ([BrandID])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Branch]
GO
ALTER TABLE [dbo].[product_info]  WITH CHECK ADD FOREIGN KEY([productInfoStatus])
REFERENCES [dbo].[ProductStatus] ([StatusID])
GO
ALTER TABLE [dbo].[product_info]  WITH CHECK ADD FOREIGN KEY([productID])
REFERENCES [dbo].[Product] ([ProductID])
GO
ALTER TABLE [dbo].[product_info]  WITH CHECK ADD FOREIGN KEY([productInfoStatus])
REFERENCES [dbo].[ProductStatus] ([StatusID])
GO
ALTER TABLE [dbo].[product_info]  WITH CHECK ADD FOREIGN KEY([productID])
REFERENCES [dbo].[Product] ([ProductID])
GO
ALTER TABLE [dbo].[ProductImage]  WITH CHECK ADD FOREIGN KEY([ProductInfoID])
REFERENCES [dbo].[product_info] ([productInfoID])
GO
ALTER TABLE [dbo].[ProductImage]  WITH CHECK ADD FOREIGN KEY([ProductInfoID])
REFERENCES [dbo].[product_info] ([productInfoID])
GO
USE [master]
GO
ALTER DATABASE [PhoneStore10] SET  READ_WRITE 
GO
