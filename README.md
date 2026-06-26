# Sistema de Gestión E-commerce - TechLab

Proyecto final desarrollado para el programa Talento Tech (Gobierno de la Ciudad de Buenos Aires). Este sistema es una API REST profesional construida con Spring Boot, diseñada para gestionar un catálogo de productos, inventario en tiempo real y procesamiento de pedidos de un e-commerce.


## Funcionalidades principales

El sistema ofrece una gestión integral orientada a la eficiencia y la integridad de datos:

* Gestión de Catálogo (CRUD): Creación, lectura, actualización y eliminación de productos con persistencia real en base de datos.

* Procesamiento de Órdenes Inteligente: Creación de pedidos mediante la recepción de una estructura JSON, con cálculo automatizado de subtotales por ítem y total general.

* Gestión de Inventario (Stock): Control dinámico de existencias que se ajustan automáticamente según las operaciones de compra.

* Interoperabilidad: API REST diseñada bajo principios de desacoplamiento, permitiendo su integración con cualquier cliente web o aplicación móvil y facilitando la total independencia de la capa de presentación (frontend).


## Tecnologías y Arquitectura

El sistema está construido sobre una arquitectura RESTful de alta disponibilidad, diseñada para separar las responsabilidades de la lógica de negocio, el acceso a datos y la presentación.

**Stack Tecnológico**
* Backend: Java 17+ con Spring Boot 3.

* Gestión de Base de Datos: MySQL utilizando Spring Data JPA & Hibernate para la persistencia de entidades.
* API Design: Estándar REST basado en JSON, con validación de modelos (@Valid) y manejo centralizado de excepciones.
* Seguridad: Configuración de CORS para permitir el intercambio de recursos de origen cruzado, garantizando la interoperabilidad con cualquier frontend.
* Gestión de Dependencias: Maven, asegurando la estabilidad y trazabilidad del ciclo de vida del proyecto.  

<br>

**Arquitectura de Software**

El proyecto implementa un patrón de diseño en capas, lo que garantiza un código limpio, mantenible y escalable:
* Controller Layer: Expone los endpoints de la API y gestiona las solicitudes HTTP.
* Service Layer: Contiene la lógica de negocio, validaciones y cálculos financieros (subtotales, stock, etc.).
* Repository Layer: Abstrae la comunicación con la base de datos mediante interfaces JPA.
* Model Layer: Define las entidades del sistema y sus relaciones relacionales.
* Exception Layer: Implementa un gestor global de errores para una respuesta estandarizada ante fallos.


## Endpoints Principales

A continuación, se detallan los recursos disponibles en la API:

### Productos (`/productos`)

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/productos` | Lista todo el catálogo de productos. |
| `GET` | `/productos/{id}` | Busca un producto específico por ID. |
| `GET` | `/productos/nombre/{nombre}` | Filtra productos por nombre. |
| `GET` | `/productos/categoria/{categoria}` | Filtra productos por categoría. |
| `GET` | `/productos/filtro-precio` | Filtra productos dentro de un rango de precios mínimo y máximo.  |
| `GET` | `/productos/stock-bajo/{limite}` | Alerta sobre productos cuyo stock sea inferior al límite dado. |
| `GET` | `/productos/marca/{marca}` | Recupera todos los productos de una marca  específica. |
| `GET` | `/productos/mas-caro` | Retorna el o los productos con el precio más alto del catálogo. |
| `POST` | `/productos` | Crea un nuevo producto. |
| `PUT` | `/productos/{id}` | Actualiza la información completa de un producto por su ID. |
| `DELETE` | `/productos/{id}` | Elimina un producto. |

Para interactuar con la API mediante peticiones POST, se deben enviar los datos del producto en formato JSON según la siguiente estructura:

```json
{
  "nombre": "Nombre del Producto",
  "precio": 1,
  "marca": "Marca",
  "categoria": {
    "id": 1
  },
  "descripcion": "Descripción detallada",
  "stock": 1,
  "urlImagen": "URL de la imagen"
}
```

### Pedidos (`/pedidos`)

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/pedidos` | Lista el historial completo de todos los pedidos realizados. |
| `POST` | `/pedidos` | Crea un nuevo pedido (gestiona stock y subtotales automáticamente). |
| `DELETE` | `/pedidos/{id}` | Elimina un pedido y restituye el stock al inventario. |

Para interactuar con la API mediante peticiones POST, se deben enviar los datos del pedido en formato JSON según la siguiente estructura:
```json
{
  "lineas": [
    {
      "producto": { "id": 1, "precio": 0.0, "stock": 0 },
      "cantidad": 1
    },
    ...
  ]
}
```

### Categorías (`/categorias`)

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/categorias` | Lista todas las categorías disponibles. |
| `GET` | `/categorias/{id}` | Obtiene una categoría específica por ID. |
| `POST` | `/categorias` | Crea una nueva categoría para clasificar productos. |
| `PUT` | `/categorias/{id}` | Modifica los datos de una categoría existente por su ID.|
| `DELETE` | `/categorias/{id}` | Elimina físicamente una categoría del listado por su ID.|

Para interactuar con la API mediante peticiones POST, se deben enviar los datos de la categoria en formato JSON según la siguiente estructura:
```json
{
  "nombre": "Nombre de la categoria",
  "descripcion": "Descripción detallada"
}
```

## Estructura final del proyecto

```text
com.techlab.ecommerce
├── config
│   └── WebConfig.java
├── controller
│   ├── ProductoController.java
│   ├── CategoriaController.java
│   └── PedidoController.java
├── exception
│   ├── GlobalExceptionHandler.java
│   ├── PedidoNoEncontradoException.java
│   ├── CategoriaNoEncontradaException.java
│   ├── CategoriaNombreInvalidoException.java
│   ├── ProductoNoEncontradoException.java
|   ├── PrecioInvalidoException.java
│   └── StockInsuficienteException.java
├── model
│   ├── Producto.java
│   ├── Categoria.java
|   ├── Pedido.java
│   └── LineaPedido.java
├── repository
│   ├── ProductoRepository.java
│   ├── CategoriaRepository.java
│   └── PedidoRepository.java
├── service
│   ├── ProductoService.java
│   ├── CategoriaService.java
│   └── PedidoService.java
└── EcommerceApplication.java
```

## Capa de Presentación (Frontend)
Para realizar las pruebas operativas y la validación de los endpoints transaccionales, se desarrolló una interfaz cliente desacoplada de forma complementaria. 

El código fuente de este cliente web (HTML5, Bootstrap 5 y JavaScript ES6) se encuentra alojado en su propio repositorio independiente para mantener la separación de responsabilidades: https://github.com/EmanuelEspinosa/TechLab-Interfaz-Cliente.git


## Autor

Proyecto realizado por: Emanuel Roberto Espinosa.  
Curso: Java Backend - Talento Tech.  
Año: 2026