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
  "precio": precio en formato entero o decimal,
  "marca": "Marca",
  "categoria": {
    "id": id de la categoria (en formato nuemerico)
  },
  "descripcion": "Descripción detallada",
  "stock": Cantidad numerica,
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
      "producto": { "id": Id del producto (en formato numerico), "precio": 0.0, "stock": 0 },
      "cantidad": cantidad numerica
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

Para interactuar con la API mediante peticiones POST, se deben enviar los datos de la categoria en formato JSON según la siguiente estructura:
```json
{
  "nombre": "Nombre de la categoria",
  "descripcion": "Descripción detallada"
}
```

## Autor

Proyecto realizado por: Emanuel Roberto Espinosa.

Curso: Java Backend - Talento Tech. 

Año: 2026