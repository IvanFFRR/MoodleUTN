CREATE TABLE Alumnos(
	id INT NOT NULL IDENTITY(1000,1) PRIMARY KEY,
	legajo INT NOT NULL,
	nombre NVARCHAR(50) NOT NULL,
	apellido NVARCHAR(50) NOT NULL,
	tipoDocumento INT NOT NULL,
	numeroDocumento INT NOT NULL,
	fechaNacimiento DATE NOT NULL,
	calle NVARCHAR(50),
	altura INT,
	codigoPostal INT,
	email NVARCHAR(50) NOT NULL,
	telefono VARCHAR(50),
	CONSTRAINT FK_AlumnosTipoDocumento FOREIGN KEY (tipoDocumento) REFERENCES TiposDocumento (id)
)



CREATE TABLE Profesores(
	id INT NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	legajo INT NOT NULL,
	nombre NVARCHAR(50) NOT NULL,
	apellido NVARCHAR(50) NOT NULL,
	tipoDocumento INT NOT NULL,
	documento INT NOT NULL,
	fechaNacimiento DATE,
	email NVARCHAR(50) NOT NULL,
	CONSTRAINT FK_ProfesoresTipoDocumento FOREIGN KEY (tipoDocumento) REFERENCES TiposDocumento (id)
)


CREATE TABLE Materias( 
	id INT NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	nombre NVARCHAR(50),
	profesor INT,
	CONSTRAINT FK_MateriasProfesor FOREIGN KEY (profesor) REFERENCES Profesores(id)
)

CREATE TABLE Inscripciones(
	id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	fecha DATE NOT NULL,
	materia INT NOT NULL,
	alumno INT NOT NULL,
	CONSTRAINT FK_InscripcionesAlumno FOREIGN KEY (alumno) REFERENCES Alumnos(id),
	CONSTRAINT FK_InscripcionesMateria FOREIGN KEY (materia) REFERENCES Materias(id)
)

CREATE TABLE Recursos( 
	id UNIQUEIDENTIFIER NOT NULL ROWGUIDCOL PRIMARY KEY,
	fecha DATE NOT NULL,
	materia INT NOT NULL,
	recurso VARBINARY(MAX) FILESTREAM
)

CREATE TABLE Descargas(
		id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
		fecha DATE NOT NULL,
		alumno INT,
		recurso UNIQUEIDENTIFIER NOT NULL,
		CONSTRAINT FK_DescargasAlumno FOREIGN KEY (alumno) REFERENCES Alumnos(id),
		CONSTRAINT FK_DescargasRecurso FOREIGN KEY (recurso) REFERENCES Recursos(id)
)

CREATE TABLE Login(
	id INT NOT NULL PRIMARY KEY,
	fecha DATE DEFAULT GETDATE(),
	legajo INT NOT NULL,
	documento INT NOT NULL,
)

CREATE PROC mostrarErrores
AS
PRINT N'La transacción se ha completado con errores'
SELECT ERROR_NUMBER() AS 'Número de error', 
		ERROR_SEVERITY() AS 'Severidad', 
		ERROR_STATE() AS 'Estado', 
		ERROR_LINE() AS 'Línea', 
		ERROR_PROCEDURE() AS 'Proc/Trigger', 
		ERROR_MESSAGE() AS 'Mensaje'

CREATE FUNCTION dbo.ExisteUsuario (@user INT, @pass INT) 
RETURNS BIT
	AS
		BEGIN
			DECLARE @bool BIT
			SELECT @bool = COUNT(id) FROM Alumnos WHERE legajo = @user AND numeroDocumento = @pass
			IF (@bool IS NULL)
				SET @bool = 0
			RETURN @bool
		END

CREATE PROC LoginAlumno
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


