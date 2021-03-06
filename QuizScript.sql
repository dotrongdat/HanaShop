USE [master]
GO
/****** Object:  Database [QuizOnlineDB]    Script Date: 3/9/2021 9:03:37 AM ******/
CREATE DATABASE [QuizOnlineDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QuizOnlineDB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\QuizOnlineDB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QuizOnlineDB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\QuizOnlineDB_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuizOnlineDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuizOnlineDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuizOnlineDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuizOnlineDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuizOnlineDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuizOnlineDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuizOnlineDB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QuizOnlineDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuizOnlineDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuizOnlineDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuizOnlineDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuizOnlineDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuizOnlineDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuizOnlineDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuizOnlineDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuizOnlineDB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QuizOnlineDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuizOnlineDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuizOnlineDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuizOnlineDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuizOnlineDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuizOnlineDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuizOnlineDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuizOnlineDB] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QuizOnlineDB] SET  MULTI_USER 
GO
ALTER DATABASE [QuizOnlineDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuizOnlineDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuizOnlineDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuizOnlineDB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QuizOnlineDB] SET DELAYED_DURABILITY = DISABLED 
GO
USE [QuizOnlineDB]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 3/9/2021 9:03:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[Email] [varchar](30) NOT NULL,
	[Name] [nvarchar](max) NOT NULL,
	[Password] [varchar](max) NOT NULL,
	[Admin] [bit] NOT NULL,
	[Status] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Answer]    Script Date: 3/9/2021 9:03:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Answer](
	[AnsID] [varchar](30) NOT NULL,
	[QuestID] [varchar](30) NOT NULL,
	[Content] [nvarchar](max) NOT NULL,
	[CorrectAnswer] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[AnsID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Question]    Script Date: 3/9/2021 9:03:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Question](
	[QuestID] [varchar](30) NOT NULL,
	[SubID] [varchar](30) NOT NULL,
	[Content] [nvarchar](max) NOT NULL,
	[CreateDate] [datetime] NOT NULL,
	[Status] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[QuestID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ResultSubject]    Script Date: 3/9/2021 9:03:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ResultSubject](
	[SubID] [varchar](30) NOT NULL,
	[Email] [varchar](30) NOT NULL,
	[Mark] [float] NOT NULL,
	[DateTakeQuiz] [datetime] NOT NULL,
	[DurationTakeQuiz] [bigint] NOT NULL,
	[Data] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK__ResultSu__0706A839002AD846] PRIMARY KEY CLUSTERED 
(
	[SubID] ASC,
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Subject]    Script Date: 3/9/2021 9:03:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Subject](
	[SubID] [varchar](30) NOT NULL,
	[SubCodeName] [varchar](30) NOT NULL,
	[SubName] [nvarchar](max) NOT NULL,
	[CreateDate] [datetime] NOT NULL,
	[Duration] [bigint] NOT NULL,
	[Status] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[SubID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
INSERT [dbo].[Account] ([Email], [Name], [Password], [Admin], [Status]) VALUES (N'datdtse140920@fpt.edu.vn', N'Đỗ Trọng Đạt', N'22C377DAF265F42E11A57958295C4DA076A6850BDEA51C22FB8B609EB2B55897', 1, 1)
INSERT [dbo].[Account] ([Email], [Name], [Password], [Admin], [Status]) VALUES (N'gonass2000@gmail.com', N'gonass', N'15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', 0, 1)
INSERT [dbo].[Account] ([Email], [Name], [Password], [Admin], [Status]) VALUES (N'test@gmail.com', N'test', N'15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', 0, 1)
INSERT [dbo].[Account] ([Email], [Name], [Password], [Admin], [Status]) VALUES (N'trongdat2000@gmail.com', N'Trọng Đạt', N'15E2B0D3C33891EBB0F1EF609EC419420C20E320CE94C65FBC8C3312448EB225', 0, 1)
INSERT [dbo].[Account] ([Email], [Name], [Password], [Admin], [Status]) VALUES (N'tronghuy@gmail.com', N'Trọng Huy', N'15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', 0, 0)
GO
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_1_1', N'1_1', N'Each node can be reachable from the root
through some paths.', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_1_2', N'1_1', N'Path is number of arcs.', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_1_3', N'1_1', N'The level of a node is the length of the path from the root to the node plus 1.', 1)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_1_4', N'1_1', N'The height of a nonempty tree is the maximum level of a node in the tree.', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_10_1', N'1_10', N'1', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_10_2', N'1_10', N'1', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_10_3', N'1_10', N'1', 1)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_10_4', N'1_10', N'1', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_11_1', N'1_11', N'2', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_11_2', N'1_11', N'2', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_11_3', N'1_11', N'2', 1)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_11_4', N'1_11', N'2', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_2_1', N'1_2', N'Breath-First traversal is implemented using queue', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_2_2', N'1_2', N'For a binary tree with n nodes, there are n! different traversals', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_2_3', N'1_2', N'The complexity of searching a node is the length of the path leading to this node', 1)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_2_4', N'1_2', N'A search can takes lg ( n ) time units in the worst case', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_3_1', N'1_3', N'Breadth-first search.', 1)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_3_2', N'1_3', N'Depth-first search', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_3_3', N'1_3', N'All of the others', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_3_4', N'1_3', N'None of the others', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_4_1', N'1_4', N'Breath-First Traversal', 1)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_4_2', N'1_4', N'Depth-First Traversal', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_4_3', N'1_4', N'Stackless Depth-First Traversal', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_4_4', N'1_4', N'None of the others', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_5_1', N'1_5', N'10', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_5_2', N'1_5', N'15', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_5_3', N'1_5', N'20', 1)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_5_4', N'1_5', N'30', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_6_1', N'1_6', N'1', 1)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_6_2', N'1_6', N'2', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_6_3', N'1_6', N'4', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_6_4', N'1_6', N'3', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_7_1', N'1_7', N'1', 1)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_7_2', N'1_7', N'2', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_7_3', N'1_7', N'3', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_7_4', N'1_7', N'4', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_8_1', N'1_8', N'1', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_8_2', N'1_8', N'1', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_8_3', N'1_8', N'1', 1)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_8_4', N'1_8', N'1', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_9_1', N'1_9', N'2', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_9_2', N'1_9', N'2', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_9_3', N'1_9', N'2', 1)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'1_9_4', N'1_9', N'2', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'2_1_1', N'2_1', N'1', 1)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'2_1_2', N'2_1', N'2', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'2_1_3', N'2_1', N'3', 0)
INSERT [dbo].[Answer] ([AnsID], [QuestID], [Content], [CorrectAnswer]) VALUES (N'2_1_4', N'2_1', N'4', 0)
GO
INSERT [dbo].[Question] ([QuestID], [SubID], [Content], [CreateDate], [Status]) VALUES (N'1_1', N'1', N'Which of the following concepts of tree are true:', CAST(N'2021-02-03T17:15:05.843' AS DateTime), 1)
INSERT [dbo].[Question] ([QuestID], [SubID], [Content], [CreateDate], [Status]) VALUES (N'1_10', N'1', N'1', CAST(N'2021-03-09T00:31:51.667' AS DateTime), 0)
INSERT [dbo].[Question] ([QuestID], [SubID], [Content], [CreateDate], [Status]) VALUES (N'1_11', N'1', N'2', CAST(N'2021-03-09T00:31:51.667' AS DateTime), 0)
INSERT [dbo].[Question] ([QuestID], [SubID], [Content], [CreateDate], [Status]) VALUES (N'1_2', N'1', N'Select correct statement', CAST(N'2021-02-03T17:55:33.330' AS DateTime), 1)
INSERT [dbo].[Question] ([QuestID], [SubID], [Content], [CreateDate], [Status]) VALUES (N'1_3', N'1', N'What graph traversal algorithm uses a queue to keep track of vertices which need to', CAST(N'2021-02-03T18:14:12.333' AS DateTime), 1)
INSERT [dbo].[Question] ([QuestID], [SubID], [Content], [CreateDate], [Status]) VALUES (N'1_4', N'1', N'......... will visit nodes of a tree starting from the highest (or lowest) level and moving down (or up) level by level and at a level, it visits nodes from left to right (or from right to left)', CAST(N'2021-02-03T18:24:04.243' AS DateTime), 1)
INSERT [dbo].[Question] ([QuestID], [SubID], [Content], [CreateDate], [Status]) VALUES (N'1_5', N'1', N'A balanced binary search tree containing one million nodes. What is the maximum
number of comparisons needed to find a key in the tree', CAST(N'2021-02-03T18:24:04.243' AS DateTime), 0)
INSERT [dbo].[Question] ([QuestID], [SubID], [Content], [CreateDate], [Status]) VALUES (N'1_6', N'1', N'abcd', CAST(N'2021-03-03T08:00:44.873' AS DateTime), 0)
INSERT [dbo].[Question] ([QuestID], [SubID], [Content], [CreateDate], [Status]) VALUES (N'1_7', N'1', N'test', CAST(N'2021-03-05T07:44:44.690' AS DateTime), 0)
INSERT [dbo].[Question] ([QuestID], [SubID], [Content], [CreateDate], [Status]) VALUES (N'1_8', N'1', N'1', CAST(N'2021-03-09T00:30:37.853' AS DateTime), 0)
INSERT [dbo].[Question] ([QuestID], [SubID], [Content], [CreateDate], [Status]) VALUES (N'1_9', N'1', N'2', CAST(N'2021-03-09T00:30:37.853' AS DateTime), 0)
INSERT [dbo].[Question] ([QuestID], [SubID], [Content], [CreateDate], [Status]) VALUES (N'2_1', N'2', N'123456789', CAST(N'2021-03-05T07:39:23.727' AS DateTime), 1)
GO
INSERT [dbo].[ResultSubject] ([SubID], [Email], [Mark], [DateTakeQuiz], [DurationTakeQuiz], [Data]) VALUES (N'1', N'gonass2000@gmail.com', 1.6666666666666667, CAST(N'2021-03-05T07:40:43.877' AS DateTime), 19, N'rO0ABXNyACdkYXRkdC5yZXN1bHRzdWJqZWN0LlJlc3VsdFN1YmplY3RPYmplY3TG9OQ3ewWDAgIAAUwACXF1ZXN0aW9uc3QAEExqYXZhL3V0aWwvTGlzdDt4cHNyABNqYXZhLnV0aWwuQXJyYXlMaXN0eIHSHZnHYZ0DAAFJAARzaXpleHAAAAAGdwQAAAAGc3IAKGRhdGR0LnJlc3VsdHN1YmplY3QuUmVzdWx0UXVlc3Rpb25PYmplY3QGn4NARLyTxwIAAkwAB2Fuc3dlcnNxAH4AAUwAB2NvbnRlbnR0ABJMamF2YS9sYW5nL1N0cmluZzt4cHNxAH4AAwAAAAR3BAAAAARzcgAmZGF0ZHQucmVzdWx0c3ViamVjdC5SZXN1bHRBbnN3ZXJPYmplY3RFuo9DppnwWAIAA1oAB2Nob29zZW5aAA1jb3JyZWN0QW5zd2VyTAAHY29udGVudHEAfgAGeHAAAHQAAjEwc3EAfgAJAAF0AAIyMHNxAH4ACQAAdAACMzBzcQB+AAkAAHQAAjE1eHQAh0EgYmFsYW5jZWQgYmluYXJ5IHNlYXJjaCB0cmVlIGNvbnRhaW5pbmcgb25lIG1pbGxpb24gbm9kZXMuIFdoYXQgaXMgdGhlIG1heGltdW0NCm51bWJlciBvZiBjb21wYXJpc29ucyBuZWVkZWQgdG8gZmluZCBhIGtleSBpbiB0aGUgdHJlZXNxAH4ABXNxAH4AAwAAAAR3BAAAAARzcQB+AAkAAHQASVRoZSBoZWlnaHQgb2YgYSBub25lbXB0eSB0cmVlIGlzIHRoZSBtYXhpbXVtIGxldmVsIG9mIGEgbm9kZSBpbiB0aGUgdHJlZS5zcQB+AAkAAHQAPUVhY2ggbm9kZSBjYW4gYmUgcmVhY2hhYmxlIGZyb20gdGhlIHJvb3QNCnRocm91Z2ggc29tZSBwYXRocy5zcQB+AAkAAXQAT1RoZSBsZXZlbCBvZiBhIG5vZGUgaXMgdGhlIGxlbmd0aCBvZiB0aGUgcGF0aCBmcm9tIHRoZSByb290IHRvIHRoZSBub2RlIHBsdXMgMS5zcQB+AAkAAHQAF1BhdGggaXMgbnVtYmVyIG9mIGFyY3MueHQAMVdoaWNoIG9mIHRoZSBmb2xsb3dpbmcgY29uY2VwdHMgb2YgdHJlZSBhcmUgdHJ1ZTpzcQB+AAVzcQB+AAMAAAAEdwQAAAAEc3EAfgAJAAB0ABJOb25lIG9mIHRoZSBvdGhlcnNzcQB+AAkAAXQAFkJyZWF0aC1GaXJzdCBUcmF2ZXJzYWxzcQB+AAkAAHQAFURlcHRoLUZpcnN0IFRyYXZlcnNhbHNxAH4ACQAAdAAfU3RhY2tsZXNzIERlcHRoLUZpcnN0IFRyYXZlcnNhbHh0AMIuLi4uLi4uLi4gd2lsbCB2aXNpdCBub2RlcyBvZiBhIHRyZWUgc3RhcnRpbmcgZnJvbSB0aGUgaGlnaGVzdCAob3IgbG93ZXN0KSBsZXZlbCBhbmQgbW92aW5nIGRvd24gKG9yIHVwKSBsZXZlbCBieSBsZXZlbCBhbmQgYXQgYSBsZXZlbCwgaXQgdmlzaXRzIG5vZGVzIGZyb20gbGVmdCB0byByaWdodCAob3IgZnJvbSByaWdodCB0byBsZWZ0KXNxAH4ABXNxAH4AAwAAAAR3BAAAAARzcQB+AAkBAXQAATFzcQB+AAkAAHQAATRzcQB+AAkAAHQAATJzcQB+AAkAAHQAATN4dAAEYWJjZHNxAH4ABXNxAH4AAwAAAAR3BAAAAARzcQB+AAkAAXQAUVRoZSBjb21wbGV4aXR5IG9mIHNlYXJjaGluZyBhIG5vZGUgaXMgdGhlIGxlbmd0aCBvZiB0aGUgcGF0aCBsZWFkaW5nIHRvIHRoaXMgbm9kZXNxAH4ACQAAdAAxQnJlYXRoLUZpcnN0IHRyYXZlcnNhbCBpcyBpbXBsZW1lbnRlZCB1c2luZyBxdWV1ZXNxAH4ACQAAdABBRm9yIGEgYmluYXJ5IHRyZWUgd2l0aCBuIG5vZGVzLCB0aGVyZSBhcmUgbiEgZGlmZmVyZW50IHRyYXZlcnNhbHNzcQB+AAkAAHQAOEEgc2VhcmNoIGNhbiB0YWtlcyBsZyAoIG4gKSB0aW1lIHVuaXRzIGluIHRoZSB3b3JzdCBjYXNleHQAGFNlbGVjdCBjb3JyZWN0IHN0YXRlbWVudHNxAH4ABXNxAH4AAwAAAAR3BAAAAARzcQB+AAkAAHQAEUFsbCBvZiB0aGUgb3RoZXJzc3EAfgAJAAB0ABJOb25lIG9mIHRoZSBvdGhlcnNzcQB+AAkAAXQAFUJyZWFkdGgtZmlyc3Qgc2VhcmNoLnNxAH4ACQAAdAASRGVwdGgtZmlyc3Qgc2VhcmNoeHQAU1doYXQgZ3JhcGggdHJhdmVyc2FsIGFsZ29yaXRobSB1c2VzIGEgcXVldWUgdG8ga2VlcCB0cmFjayBvZiB2ZXJ0aWNlcyB3aGljaCBuZWVkIHRveA==')
INSERT [dbo].[ResultSubject] ([SubID], [Email], [Mark], [DateTakeQuiz], [DurationTakeQuiz], [Data]) VALUES (N'1', N'test@gmail.com', 1.4285714285714286, CAST(N'2021-03-05T07:45:30.007' AS DateTime), 16, N'rO0ABXNyACdkYXRkdC5yZXN1bHRzdWJqZWN0LlJlc3VsdFN1YmplY3RPYmplY3TG9OQ3ewWDAgIAAUwACXF1ZXN0aW9uc3QAEExqYXZhL3V0aWwvTGlzdDt4cHNyABNqYXZhLnV0aWwuQXJyYXlMaXN0eIHSHZnHYZ0DAAFJAARzaXpleHAAAAAHdwQAAAAHc3IAKGRhdGR0LnJlc3VsdHN1YmplY3QuUmVzdWx0UXVlc3Rpb25PYmplY3QGn4NARLyTxwIAAkwAB2Fuc3dlcnNxAH4AAUwAB2NvbnRlbnR0ABJMamF2YS9sYW5nL1N0cmluZzt4cHNxAH4AAwAAAAR3BAAAAARzcgAmZGF0ZHQucmVzdWx0c3ViamVjdC5SZXN1bHRBbnN3ZXJPYmplY3RFuo9DppnwWAIAA1oAB2Nob29zZW5aAA1jb3JyZWN0QW5zd2VyTAAHY29udGVudHEAfgAGeHABAHQAPUVhY2ggbm9kZSBjYW4gYmUgcmVhY2hhYmxlIGZyb20gdGhlIHJvb3QNCnRocm91Z2ggc29tZSBwYXRocy5zcQB+AAkAAHQASVRoZSBoZWlnaHQgb2YgYSBub25lbXB0eSB0cmVlIGlzIHRoZSBtYXhpbXVtIGxldmVsIG9mIGEgbm9kZSBpbiB0aGUgdHJlZS5zcQB+AAkAAHQAF1BhdGggaXMgbnVtYmVyIG9mIGFyY3Muc3EAfgAJAAF0AE9UaGUgbGV2ZWwgb2YgYSBub2RlIGlzIHRoZSBsZW5ndGggb2YgdGhlIHBhdGggZnJvbSB0aGUgcm9vdCB0byB0aGUgbm9kZSBwbHVzIDEueHQAMVdoaWNoIG9mIHRoZSBmb2xsb3dpbmcgY29uY2VwdHMgb2YgdHJlZSBhcmUgdHJ1ZTpzcQB+AAVzcQB+AAMAAAAEdwQAAAAEc3EAfgAJAAB0ABVEZXB0aC1GaXJzdCBUcmF2ZXJzYWxzcQB+AAkAAHQAH1N0YWNrbGVzcyBEZXB0aC1GaXJzdCBUcmF2ZXJzYWxzcQB+AAkAAHQAEk5vbmUgb2YgdGhlIG90aGVyc3NxAH4ACQABdAAWQnJlYXRoLUZpcnN0IFRyYXZlcnNhbHh0AMIuLi4uLi4uLi4gd2lsbCB2aXNpdCBub2RlcyBvZiBhIHRyZWUgc3RhcnRpbmcgZnJvbSB0aGUgaGlnaGVzdCAob3IgbG93ZXN0KSBsZXZlbCBhbmQgbW92aW5nIGRvd24gKG9yIHVwKSBsZXZlbCBieSBsZXZlbCBhbmQgYXQgYSBsZXZlbCwgaXQgdmlzaXRzIG5vZGVzIGZyb20gbGVmdCB0byByaWdodCAob3IgZnJvbSByaWdodCB0byBsZWZ0KXNxAH4ABXNxAH4AAwAAAAR3BAAAAARzcQB+AAkAAHQAMUJyZWF0aC1GaXJzdCB0cmF2ZXJzYWwgaXMgaW1wbGVtZW50ZWQgdXNpbmcgcXVldWVzcQB+AAkAAHQAOEEgc2VhcmNoIGNhbiB0YWtlcyBsZyAoIG4gKSB0aW1lIHVuaXRzIGluIHRoZSB3b3JzdCBjYXNlc3EAfgAJAAB0AEFGb3IgYSBiaW5hcnkgdHJlZSB3aXRoIG4gbm9kZXMsIHRoZXJlIGFyZSBuISBkaWZmZXJlbnQgdHJhdmVyc2Fsc3NxAH4ACQABdABRVGhlIGNvbXBsZXhpdHkgb2Ygc2VhcmNoaW5nIGEgbm9kZSBpcyB0aGUgbGVuZ3RoIG9mIHRoZSBwYXRoIGxlYWRpbmcgdG8gdGhpcyBub2RleHQAGFNlbGVjdCBjb3JyZWN0IHN0YXRlbWVudHNxAH4ABXNxAH4AAwAAAAR3BAAAAARzcQB+AAkAAHQAAjEwc3EAfgAJAAF0AAIyMHNxAH4ACQAAdAACMzBzcQB+AAkAAHQAAjE1eHQAh0EgYmFsYW5jZWQgYmluYXJ5IHNlYXJjaCB0cmVlIGNvbnRhaW5pbmcgb25lIG1pbGxpb24gbm9kZXMuIFdoYXQgaXMgdGhlIG1heGltdW0NCm51bWJlciBvZiBjb21wYXJpc29ucyBuZWVkZWQgdG8gZmluZCBhIGtleSBpbiB0aGUgdHJlZXNxAH4ABXNxAH4AAwAAAAR3BAAAAARzcQB+AAkAAHQAEk5vbmUgb2YgdGhlIG90aGVyc3NxAH4ACQAAdAARQWxsIG9mIHRoZSBvdGhlcnNzcQB+AAkAAHQAEkRlcHRoLWZpcnN0IHNlYXJjaHNxAH4ACQABdAAVQnJlYWR0aC1maXJzdCBzZWFyY2gueHQAU1doYXQgZ3JhcGggdHJhdmVyc2FsIGFsZ29yaXRobSB1c2VzIGEgcXVldWUgdG8ga2VlcCB0cmFjayBvZiB2ZXJ0aWNlcyB3aGljaCBuZWVkIHRvc3EAfgAFc3EAfgADAAAABHcEAAAABHNxAH4ACQAAdAABM3NxAH4ACQAAdAABMnNxAH4ACQABdAABMXNxAH4ACQAAdAABNHh0AARhYmNkc3EAfgAFc3EAfgADAAAABHcEAAAABHNxAH4ACQAAdAABM3NxAH4ACQEBdAABMXNxAH4ACQAAdAABMnNxAH4ACQAAdAABNHh0AAR0ZXN0eA==')
INSERT [dbo].[ResultSubject] ([SubID], [Email], [Mark], [DateTakeQuiz], [DurationTakeQuiz], [Data]) VALUES (N'1', N'trongdat2000@gmail.com', 8, CAST(N'2021-03-03T02:07:09.793' AS DateTime), 120, N'rO0ABXNyACdkYXRkdC5yZXN1bHRzdWJqZWN0LlJlc3VsdFN1YmplY3RPYmplY3TG9OQ3ewWDAgIAAUwACXF1ZXN0aW9uc3QAEExqYXZhL3V0aWwvTGlzdDt4cHNyABNqYXZhLnV0aWwuQXJyYXlMaXN0eIHSHZnHYZ0DAAFJAARzaXpleHAAAAAFdwQAAAAFc3IAKGRhdGR0LnJlc3VsdHN1YmplY3QuUmVzdWx0UXVlc3Rpb25PYmplY3QGn4NARLyTxwIAAkwAB2Fuc3dlcnNxAH4AAUwAB2NvbnRlbnR0ABJMamF2YS9sYW5nL1N0cmluZzt4cHNxAH4AAwAAAAR3BAAAAARzcgAmZGF0ZHQucmVzdWx0c3ViamVjdC5SZXN1bHRBbnN3ZXJPYmplY3RFuo9DppnwWAIAA1oAB2Nob29zZW5aAA1jb3JyZWN0QW5zd2VyTAAHY29udGVudHEAfgAGeHAAAHQAEkRlcHRoLWZpcnN0IHNlYXJjaHNxAH4ACQAAdAARQWxsIG9mIHRoZSBvdGhlcnNzcQB+AAkBAXQAFUJyZWFkdGgtZmlyc3Qgc2VhcmNoLnNxAH4ACQAAdAASTm9uZSBvZiB0aGUgb3RoZXJzeHQAU1doYXQgZ3JhcGggdHJhdmVyc2FsIGFsZ29yaXRobSB1c2VzIGEgcXVldWUgdG8ga2VlcCB0cmFjayBvZiB2ZXJ0aWNlcyB3aGljaCBuZWVkIHRvc3EAfgAFc3EAfgADAAAABHcEAAAABHNxAH4ACQEBdABPVGhlIGxldmVsIG9mIGEgbm9kZSBpcyB0aGUgbGVuZ3RoIG9mIHRoZSBwYXRoIGZyb20gdGhlIHJvb3QgdG8gdGhlIG5vZGUgcGx1cyAxLnNxAH4ACQAAdABJVGhlIGhlaWdodCBvZiBhIG5vbmVtcHR5IHRyZWUgaXMgdGhlIG1heGltdW0gbGV2ZWwgb2YgYSBub2RlIGluIHRoZSB0cmVlLnNxAH4ACQAAdAA9RWFjaCBub2RlIGNhbiBiZSByZWFjaGFibGUgZnJvbSB0aGUgcm9vdA0KdGhyb3VnaCBzb21lIHBhdGhzLnNxAH4ACQAAdAAXUGF0aCBpcyBudW1iZXIgb2YgYXJjcy54dAAxV2hpY2ggb2YgdGhlIGZvbGxvd2luZyBjb25jZXB0cyBvZiB0cmVlIGFyZSB0cnVlOnNxAH4ABXNxAH4AAwAAAAR3BAAAAARzcQB+AAkBAXQAAjIwc3EAfgAJAAB0AAIxMHNxAH4ACQAAdAACMTVzcQB+AAkAAHQAAjMweHQAh0EgYmFsYW5jZWQgYmluYXJ5IHNlYXJjaCB0cmVlIGNvbnRhaW5pbmcgb25lIG1pbGxpb24gbm9kZXMuIFdoYXQgaXMgdGhlIG1heGltdW0NCm51bWJlciBvZiBjb21wYXJpc29ucyBuZWVkZWQgdG8gZmluZCBhIGtleSBpbiB0aGUgdHJlZXNxAH4ABXNxAH4AAwAAAAR3BAAAAARzcQB+AAkAAHQAH1N0YWNrbGVzcyBEZXB0aC1GaXJzdCBUcmF2ZXJzYWxzcQB+AAkAAHQAFURlcHRoLUZpcnN0IFRyYXZlcnNhbHNxAH4ACQEBdAAWQnJlYXRoLUZpcnN0IFRyYXZlcnNhbHNxAH4ACQAAdAASTm9uZSBvZiB0aGUgb3RoZXJzeHQAwi4uLi4uLi4uLiB3aWxsIHZpc2l0IG5vZGVzIG9mIGEgdHJlZSBzdGFydGluZyBmcm9tIHRoZSBoaWdoZXN0IChvciBsb3dlc3QpIGxldmVsIGFuZCBtb3ZpbmcgZG93biAob3IgdXApIGxldmVsIGJ5IGxldmVsIGFuZCBhdCBhIGxldmVsLCBpdCB2aXNpdHMgbm9kZXMgZnJvbSBsZWZ0IHRvIHJpZ2h0IChvciBmcm9tIHJpZ2h0IHRvIGxlZnQpc3EAfgAFc3EAfgADAAAABHcEAAAABHNxAH4ACQEAdAA4QSBzZWFyY2ggY2FuIHRha2VzIGxnICggbiApIHRpbWUgdW5pdHMgaW4gdGhlIHdvcnN0IGNhc2VzcQB+AAkAAXQAUVRoZSBjb21wbGV4aXR5IG9mIHNlYXJjaGluZyBhIG5vZGUgaXMgdGhlIGxlbmd0aCBvZiB0aGUgcGF0aCBsZWFkaW5nIHRvIHRoaXMgbm9kZXNxAH4ACQAAdAAxQnJlYXRoLUZpcnN0IHRyYXZlcnNhbCBpcyBpbXBsZW1lbnRlZCB1c2luZyBxdWV1ZXNxAH4ACQAAdABBRm9yIGEgYmluYXJ5IHRyZWUgd2l0aCBuIG5vZGVzLCB0aGVyZSBhcmUgbiEgZGlmZmVyZW50IHRyYXZlcnNhbHN4dAAYU2VsZWN0IGNvcnJlY3Qgc3RhdGVtZW50eA==')
INSERT [dbo].[ResultSubject] ([SubID], [Email], [Mark], [DateTakeQuiz], [DurationTakeQuiz], [Data]) VALUES (N'2', N'gonass2000@gmail.com', 10, CAST(N'2021-03-05T07:40:17.387' AS DateTime), 5, N'rO0ABXNyACdkYXRkdC5yZXN1bHRzdWJqZWN0LlJlc3VsdFN1YmplY3RPYmplY3TG9OQ3ewWDAgIAAUwACXF1ZXN0aW9uc3QAEExqYXZhL3V0aWwvTGlzdDt4cHNyABNqYXZhLnV0aWwuQXJyYXlMaXN0eIHSHZnHYZ0DAAFJAARzaXpleHAAAAABdwQAAAABc3IAKGRhdGR0LnJlc3VsdHN1YmplY3QuUmVzdWx0UXVlc3Rpb25PYmplY3QGn4NARLyTxwIAAkwAB2Fuc3dlcnNxAH4AAUwAB2NvbnRlbnR0ABJMamF2YS9sYW5nL1N0cmluZzt4cHNxAH4AAwAAAAR3BAAAAARzcgAmZGF0ZHQucmVzdWx0c3ViamVjdC5SZXN1bHRBbnN3ZXJPYmplY3RFuo9DppnwWAIAA1oAB2Nob29zZW5aAA1jb3JyZWN0QW5zd2VyTAAHY29udGVudHEAfgAGeHABAXQAATFzcQB+AAkAAHQAATNzcQB+AAkAAHQAATRzcQB+AAkAAHQAATJ4dAAJMTIzNDU2Nzg5eA==')
GO
INSERT [dbo].[Subject] ([SubID], [SubCodeName], [SubName], [CreateDate], [Duration], [Status]) VALUES (N'1', N'CSD201', N'Data Structures And Algorithms', CAST(N'2021-01-02T00:00:00.000' AS DateTime), 120000, 1)
INSERT [dbo].[Subject] ([SubID], [SubCodeName], [SubName], [CreateDate], [Duration], [Status]) VALUES (N'2', N'OSG202', N'Operating Systems', CAST(N'2021-02-02T00:00:00.000' AS DateTime), 4500000, 1)
INSERT [dbo].[Subject] ([SubID], [SubCodeName], [SubName], [CreateDate], [Duration], [Status]) VALUES (N'3', N'PRJ321', N'Web-Based Java Application', CAST(N'2021-02-02T00:00:00.000' AS DateTime), 9000000, 1)
INSERT [dbo].[Subject] ([SubID], [SubCodeName], [SubName], [CreateDate], [Duration], [Status]) VALUES (N'4', N'CEA201', N'Computer Organization And Architecture ', CAST(N'2021-01-01T00:00:00.000' AS DateTime), 3600000, 1)
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Subject__9E57E898DDC5D7B3]    Script Date: 3/9/2021 9:03:37 AM ******/
ALTER TABLE [dbo].[Subject] ADD UNIQUE NONCLUSTERED 
(
	[SubCodeName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Answer]  WITH CHECK ADD FOREIGN KEY([QuestID])
REFERENCES [dbo].[Question] ([QuestID])
GO
ALTER TABLE [dbo].[Question]  WITH CHECK ADD FOREIGN KEY([SubID])
REFERENCES [dbo].[Subject] ([SubID])
GO
ALTER TABLE [dbo].[ResultSubject]  WITH CHECK ADD  CONSTRAINT [FK__ResultSub__Email__33D4B598] FOREIGN KEY([Email])
REFERENCES [dbo].[Account] ([Email])
GO
ALTER TABLE [dbo].[ResultSubject] CHECK CONSTRAINT [FK__ResultSub__Email__33D4B598]
GO
ALTER TABLE [dbo].[ResultSubject]  WITH CHECK ADD  CONSTRAINT [FK__ResultSub__SubID__32E0915F] FOREIGN KEY([SubID])
REFERENCES [dbo].[Subject] ([SubID])
GO
ALTER TABLE [dbo].[ResultSubject] CHECK CONSTRAINT [FK__ResultSub__SubID__32E0915F]
GO
USE [master]
GO
ALTER DATABASE [QuizOnlineDB] SET  READ_WRITE 
GO
