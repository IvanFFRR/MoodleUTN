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

