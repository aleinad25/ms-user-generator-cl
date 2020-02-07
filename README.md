# ms-user-generator-cl

Es una servicio que te permite crear un usuario en la base de datos h2.

## Instalación

El servicio se encuentra en un repositorio bitbucket publico en la rama master, para clonar el proyecto debes utilizar el siguiente comando:

```bash
git clone https://Aleinad25@bitbucket.org/Aleinad25/ms-user-generator-cl.git
```

## Ejecución del proyecto
En el terminal ejecutar el siguiente comando
```python
gradle bootRun
```

## Sobre el proyecto

El proyecto cuenta con dos operaciones HTTP
```bash
POST  http://localhost:8080/register - Descripción: Crea el usuario.

RequestBody:
{
	"name": "daniela mallea",
	"email": "daniemallea3@gmaill.com",
	"password": "Contrasena123",
	"phones":[
		{
			"number":"56986971185",
			"cityCode":"1",
			"countryCode":"1"
			
		}
	]
}

POST  http://localhost:8008/login    - Descripción: Genera un jwt token en el header.
RequestBody
{
	"name": "daniela mallea",
	"password": "Contrasena123"
}
```
