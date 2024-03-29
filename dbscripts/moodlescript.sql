USE [master]
GO
/****** Object:  Database [MoodleUTN]    Script Date: 10/11/2019 17:32:49 ******/
CREATE DATABASE [MoodleUTN]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'MoodleUTN', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\MoodleUTN.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB ), 
 FILEGROUP [FILESTREAM] CONTAINS FILESTREAM  DEFAULT
( NAME = N'MoodleUTN_filestream', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\MoodleUTN_filestream' , MAXSIZE = UNLIMITED)
 LOG ON 
( NAME = N'MoodleUTN_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\MoodleUTN_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [MoodleUTN] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [MoodleUTN].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [MoodleUTN] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [MoodleUTN] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [MoodleUTN] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [MoodleUTN] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [MoodleUTN] SET ARITHABORT OFF 
GO
ALTER DATABASE [MoodleUTN] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [MoodleUTN] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [MoodleUTN] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [MoodleUTN] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [MoodleUTN] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [MoodleUTN] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [MoodleUTN] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [MoodleUTN] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [MoodleUTN] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [MoodleUTN] SET  ENABLE_BROKER 
GO
ALTER DATABASE [MoodleUTN] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [MoodleUTN] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [MoodleUTN] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [MoodleUTN] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [MoodleUTN] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [MoodleUTN] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [MoodleUTN] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [MoodleUTN] SET RECOVERY FULL 
GO
ALTER DATABASE [MoodleUTN] SET  MULTI_USER 
GO
ALTER DATABASE [MoodleUTN] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [MoodleUTN] SET DB_CHAINING OFF 
GO
ALTER DATABASE [MoodleUTN] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [MoodleUTN] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [MoodleUTN] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'MoodleUTN', N'ON'
GO
ALTER DATABASE [MoodleUTN] SET QUERY_STORE = OFF
GO
USE [MoodleUTN]
GO
/****** Object:  UserDefinedFunction [dbo].[ExisteUsuario]    Script Date: 10/11/2019 17:32:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[ExisteUsuario] (@user INT, @pass INT) 
RETURNS BIT
	AS
		BEGIN
			DECLARE @bool BIT
			SELECT @bool = COUNT(id) FROM Alumnos WHERE legajo = @user AND numeroDocumento = @pass
			IF (@bool IS NULL)
				SET @bool = 0
			RETURN @bool
		END
GO
/****** Object:  Table [dbo].[Alumnos]    Script Date: 10/11/2019 17:32:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Alumnos](
	[id] [int] IDENTITY(1000,1) NOT NULL,
	[legajo] [int] NOT NULL,
	[nombre] [nvarchar](50) NOT NULL,
	[apellido] [nvarchar](50) NOT NULL,
	[tipoDocumento] [int] NOT NULL,
	[numeroDocumento] [int] NOT NULL,
	[fechaNacimiento] [date] NOT NULL,
	[calle] [nvarchar](50) NULL,
	[altura] [int] NULL,
	[codigoPostal] [int] NULL,
	[email] [nvarchar](50) NOT NULL,
	[telefono] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [AlumnoUnico] UNIQUE NONCLUSTERED 
(
	[legajo] ASC,
	[numeroDocumento] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Descargas]    Script Date: 10/11/2019 17:32:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Descargas](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[fecha] [date] NOT NULL,
	[alumno] [int] NULL,
	[recurso] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Inscripciones]    Script Date: 10/11/2019 17:32:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Inscripciones](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[fecha] [date] NOT NULL,
	[materia] [int] NOT NULL,
	[alumno] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Logins]    Script Date: 10/11/2019 17:32:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Logins](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[alumno] [int] NOT NULL,
	[fecha] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Materias]    Script Date: 10/11/2019 17:32:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Materias](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](50) NULL,
	[profesor] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Profesores]    Script Date: 10/11/2019 17:32:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Profesores](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[legajo] [int] NOT NULL,
	[nombre] [nvarchar](50) NOT NULL,
	[apellido] [nvarchar](50) NOT NULL,
	[tipoDocumento] [int] NOT NULL,
	[documento] [int] NOT NULL,
	[fechaNacimiento] [date] NULL,
	[email] [nvarchar](50) NOT NULL,
	[telefono] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [ProfesorUnico] UNIQUE NONCLUSTERED 
(
	[legajo] ASC,
	[documento] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Recursos]    Script Date: 10/11/2019 17:32:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Recursos](
	[fecha] [date] NOT NULL,
	[materia] [int] NOT NULL,
	[esPrivado] [bit] NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
	[recurso] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY] FILESTREAM_ON [FILESTREAM]
) ON [PRIMARY] FILESTREAM_ON [FILESTREAM]
GO
/****** Object:  Table [dbo].[TiposDocumento]    Script Date: 10/11/2019 17:32:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TiposDocumento](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[tipo] [nvarchar](12) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Recursos] ADD  DEFAULT ((0)) FOR [esPrivado]
GO
ALTER TABLE [dbo].[Alumnos]  WITH CHECK ADD  CONSTRAINT [FK_AlumnosTipoDocumento] FOREIGN KEY([tipoDocumento])
REFERENCES [dbo].[TiposDocumento] ([id])
GO
ALTER TABLE [dbo].[Alumnos] CHECK CONSTRAINT [FK_AlumnosTipoDocumento]
GO
ALTER TABLE [dbo].[Descargas]  WITH CHECK ADD  CONSTRAINT [FK_DescargasAlumno] FOREIGN KEY([alumno])
REFERENCES [dbo].[Alumnos] ([id])
GO
ALTER TABLE [dbo].[Descargas] CHECK CONSTRAINT [FK_DescargasAlumno]
GO
ALTER TABLE [dbo].[Descargas]  WITH CHECK ADD  CONSTRAINT [FK_DescargasRecurso] FOREIGN KEY([recurso])
REFERENCES [dbo].[Recursos] ([id])
GO
ALTER TABLE [dbo].[Descargas] CHECK CONSTRAINT [FK_DescargasRecurso]
GO
ALTER TABLE [dbo].[Inscripciones]  WITH CHECK ADD  CONSTRAINT [FK_InscripcionesAlumno] FOREIGN KEY([alumno])
REFERENCES [dbo].[Alumnos] ([id])
GO
ALTER TABLE [dbo].[Inscripciones] CHECK CONSTRAINT [FK_InscripcionesAlumno]
GO
ALTER TABLE [dbo].[Inscripciones]  WITH CHECK ADD  CONSTRAINT [FK_InscripcionesMateria] FOREIGN KEY([materia])
REFERENCES [dbo].[Materias] ([id])
GO
ALTER TABLE [dbo].[Inscripciones] CHECK CONSTRAINT [FK_InscripcionesMateria]
GO
ALTER TABLE [dbo].[Logins]  WITH CHECK ADD  CONSTRAINT [FK_LoginAlumno] FOREIGN KEY([alumno])
REFERENCES [dbo].[Alumnos] ([id])
GO
ALTER TABLE [dbo].[Logins] CHECK CONSTRAINT [FK_LoginAlumno]
GO
ALTER TABLE [dbo].[Materias]  WITH CHECK ADD  CONSTRAINT [FK_MateriasProfesor] FOREIGN KEY([profesor])
REFERENCES [dbo].[Profesores] ([id])
GO
ALTER TABLE [dbo].[Materias] CHECK CONSTRAINT [FK_MateriasProfesor]
GO
ALTER TABLE [dbo].[Profesores]  WITH CHECK ADD  CONSTRAINT [FK_ProfesoresTipoDocumento] FOREIGN KEY([tipoDocumento])
REFERENCES [dbo].[TiposDocumento] ([id])
GO
ALTER TABLE [dbo].[Profesores] CHECK CONSTRAINT [FK_ProfesoresTipoDocumento]
GO
/****** Object:  StoredProcedure [dbo].[LoginAlumno]    Script Date: 10/11/2019 17:32:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[LoginAlumno]
@user INT, @pass INT
AS
	BEGIN TRAN
	DECLARE @existe BIT;
	SELECT @existe = dbo.ExisteUsuario(@user, @pass)
		BEGIN TRY
			IF @existe = 1
					INSERT INTO Login(legajo, documento) VALUES(@user, @pass)
		END TRY
		BEGIN CATCH
			EXEC mostrarErrores
			IF @@TRANCOUNT > 0
				ROLLBACK TRAN
		END CATCH
		IF @@TRANCOUNT > 0
			COMMIT TRAN
GO
/****** Object:  StoredProcedure [dbo].[mostrarErrores]    Script Date: 10/11/2019 17:32:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[mostrarErrores]
AS
PRINT N'La transacción se ha completado con errores'
SELECT ERROR_NUMBER() AS 'Número de error', 
		ERROR_SEVERITY() AS 'Severidad', 
		ERROR_STATE() AS 'Estado', 
		ERROR_LINE() AS 'Línea', 
		ERROR_PROCEDURE() AS 'Proc/Trigger', 
		ERROR_MESSAGE() AS 'Mensaje'
GO
USE [master]
GO
ALTER DATABASE [MoodleUTN] SET  READ_WRITE 
GO
