
<h1 align="center">Hotel</h1>

![hotel](https://user-images.githubusercontent.com/78225423/222993633-17b5a587-734c-4993-b0eb-71a608198ff1.png)

<h4 >
Esta aplicación que sirve para la administración del servicio de un Hotel, hace parte del Challenge ONE Java.
</h4>

## :hammer: Funcionalidades

Para ingresar debe hacerse con estos datos: 
Usuario: alejandra@gmail.com
Contraseña: 123456
o
Usuario: toro@gmail.com
Contraseña:7894
- `Funcionalidad 1`: Luego de haber ingresado puede proceder a registrar una nueva reserva y huéspedes. Al momento de crear una reservación se calculará el costo de esta, basandose en los días que se quedará hospedado.
- `Funcionalidad 2`: Puede buscar reservas por medio del Id. Y huéspedes por sus apellidos.
- `Funcionalidad 3`: Permite editar y eliminar reservas y huéspedes. En caso de eliminar una reservación, se eliminarán los huéspedes relacionados a esta.


## :white_check_mark: Tecnologias
- Java
- MySQL
- Azure Pipelines

## DevOps
El proyecto cuenta con un archivo azure-pipeline.yml que permite la automatización de las pruebas de compilación del código, genera un artefacto con el jar de la aplicación, lo sube a un bucket de AWS y lo copia a una instancia de AWS. Donde se podrá probar. 
Usa variables de la librería de Azure Pipelines para mantener protegidas las credenciales para ingresar a la base de datos. 
