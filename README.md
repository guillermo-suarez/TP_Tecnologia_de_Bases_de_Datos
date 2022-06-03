# TP Final - Tecnologias Bases de Datos
## Grupo de Trabajo
- Andruszyszyn, Emanuel -
- Malazotto, Soledad - 67120
- Mezio, Santiago - 67156
- Suarez, Guillermo - 67124
## Aclaraciones
### Cambios al modelo
En CERTIPAGO:
- Se agregó la columna IDEMPRESA, es una clave foránea que representa una nueva relación (1 a N) con EMPRESA. Al momento de certificar, guardamos en el CERTIPAGO correspondiente la EMPRESA a la cual se certificó a favor esa obra.

En CONCEPTOSXCERTIF:
- Se agregó la columna IMPORTEACUANT. Se va utilizar para ir acumulando cuanto se facturó sobre un CONCEPTO en una OBRA en particular.

En CONCEPTOSXOBRA:
- Se eliminó la columna CALCFONDOREPARO. El fondo de reparo es un concepto más, por lo tanto no es necesario indicar que conceptos lo aplican.
- Se agregó la columna PORCENTAJE. Indica que porcentaje (positivo o negativo) aplica sobre el monto de avance de obra en cada certificado (CERTIPAGO).

En FOJADET:
- Se agregó la columna MONTO. Se guarda el monto a factura por el porcentaje avanzado. Rompe con la 3era forma normal (3FN) ya que estamos guardando un valor derivado y calculable. Se admitió para reducir el tiempo y la complejidad de las consultas necesarias.

### Sobre IDs autoincrementales
En general, todas las tablas caen en una de tres categorías:
**No tienen ID autoincremental:** son las tablas cuya clave primaria esta compuesta totalmente por clave foráneas y, por lo tanto, no son autoincrementales. Estas tablas son:
- CONCEPTOSXCERTIF
- CONCEPTOSXOBRA
- FOJADET
- ITEMCOSTO 

**Tienen ID autoincremental dependiente de FK:** son las tablas que tienen una clave primaria compuesta, en donde únicamente una parte es incremental y, por lo tanto, no puede resolverse con una secuencia. Estas tablas se mantendrá su ID autoincremental por procedimientos. Esto quiere decir que, por ejemplo, si agregamos un ITEM a una OBRA (no necesario en este TP) el procedimiento que realice esa tarea deberá buscar el último ID de ITEM para esa OBRA y asignarle dicho valor más uno (+1) al ID del nuevo ITEM. Las tablas que tienen este diseño son:
- CERTIPOBRA
- CERTIPAGO
- ITEM

**Tiene ID autoincremental independiente:** son las tablas que tienen una clave primaria simple y numérica y que, por lo tanto, pueden ser implementados mediante secuencias. Las tablas que utilizan secuencias para sus IDs y sus respectivas secuencias son:
- CONCEPTO usa [SEQ_ID_CONCEPTO](https://github.com/guillermo-suarez/TP_Tecnologias_Bases_de_Datos/blob/main/Secuencias/SEQ_ID_CONCEPTO).
- EMPRESA usa [SEQ_ID_EMPRESA](https://github.com/guillermo-suarez/TP_Tecnologias_Bases_de_Datos/blob/main/Secuencias/SEQ_ID_EMPRESA).
- FOJA usa [SEQ_ID_FOJA](https://github.com/guillermo-suarez/TP_Tecnologias_Bases_de_Datos/blob/main/Secuencias/SEQ_ID_FOJA.sql).
- LOCALIDAD usa [SEQ_ID_LOCALIDAD](https://github.com/guillermo-suarez/TP_Tecnologias_Bases_de_Datos/blob/main/Secuencias/SEQ_ID_LOCALIDAD.sql).
- OBRA usa [SEQ_ID_OBRA](https://github.com/guillermo-suarez/TP_Tecnologias_Bases_de_Datos/blob/main/Secuencias/SEQ_ID_OBRA).
- REDETERMINACION usa [SEQ_ID_REDETERMINACION](https://github.com/guillermo-suarez/TP_Tecnologias_Bases_de_Datos/blob/main/Secuencias/SEQ_ID_REDETERMINACION.sql).
- TIPOCONTRATACION usa [SEQ_ID_TIPOCONTRATACION](https://github.com/guillermo-suarez/TP_Tecnologias_Bases_de_Datos/blob/main/Secuencias/SEQ_ID_TIPOCONTRATACION.sql).
- TIPOITEM usa [SEQ_ID_TIPOITEM](https://github.com/guillermo-suarez/TP_Tecnologias_Bases_de_Datos/blob/main/Secuencias/SEQ_ID_TIPOITEM.sql).

## Instrucciones para la Primer Entrega
### Base de Datos Creada con los datos dados.
- Para crear las tablas, utilizar [CrearTodasLasTablas.sql](https://github.com/guillermo-suarez/TP_Tecnologias_Bases_de_Datos/blob/main/Tablas/Datos/CrearTodasLasTablas.sql). Este script crea las tablas con el [diseño dado en clase](https://campusvirtual.ugd.edu.ar/moodle/mod/resource/view.php?id=101225).
- En caso de ya tener creadas las tablas, se deberá ejecutar [BorrarTodasLasTablas.sql](https://github.com/guillermo-suarez/TP_Tecnologias_Bases_de_Datos/blob/main/Tablas/Datos/BorrarTodasLasTablas.sql) antes.
- Con las tablas creadas, deberá realizar los cambios aclarados más arriba.
- Para cargar datos a las tablas, utilizar los scripts enumerados en la carpeta [Tablas/Datos](https://github.com/guillermo-suarez/TP_Tecnologias_Bases_de_Datos/tree/main/Tablas/Datos). Respetar el orden por la enumeración de los scripts.

### Propuesta de Solución al problema planteado en el Diseño de la BD
Ver aclaraciones >> cambios al modelo.
### Mecanismo de LOG para INSERT, UPDATE y DELETE para todas las tablas
- Para crear todas las tablas a utilizar como LOGs, utilizar [CrearTodasLasTablasLOGs.sql](https://github.com/guillermo-suarez/TP_Tecnologias_Bases_de_Datos/blob/main/Tablas/LOGs/CrearTodasLasTablasLOGs.sql).
- Para crear todos los 
