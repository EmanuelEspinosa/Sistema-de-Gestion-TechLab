# Sistema de Gestión E-commerce - TechLab

Este proyecto es una simulación de un sistema de gestión de productos y pedidos para un e-commerce, desarrollado como parte de la pre-entrega del curso de **Back-End con Java** del programa **Talento Tech**, impulsado por el Gobierno de la Ciudad de Buenos Aires.

El sistema está desarrollado en Java puro, aplicando principios de programación orientada a objetos, manejo de excepciones personalizadas y separación por capas (servicios, modelos y utilidades). La interacción se realiza mediante consola, utilizando un sistema de menús para la gestión de productos y pedidos.

En esta etapa se priorizó la correcta organización del código, la reutilización de lógica mediante servicios y la validación de datos, simulando el flujo de un sistema real de e-commerce.


## Funcionalidades principales

- Gestión completa de productos (CRUD)
- Creación y administración de pedidos
- Validación de datos de entrada
- Manejo de excepciones personalizadas:
  - Producto no encontrado
  - Pedido no encontrado
  - Stock insuficiente
- Menú principal con submenú de gestión de pedidos
- Carga de datos de prueba para testing inicial
- Navegación por consola basada en opciones numéricas


## Arquitectura del proyecto

**El proyecto está estructurado en capas:**

- Model: entidades del sistema (Producto, Pedido, etc.)
- Service: lógica de negocio (ProductoService, PedidoService)
- UI: menús e interacción con el usuario
- Exception: manejo de errores personalizados
- Util: validaciones y herramientas auxiliares
- Main: punto de entrada de la aplicación


## Notas

**Esta pre-entrega del proyecto corresponde a una etapa de aprendizaje, enfocada en reforzar conceptos de:**

- Programación orientada a objetos
- Estructura de aplicaciones en Java
- Manejo de flujos de usuario por consola
- Diseño de lógica de negocio separada de la interfaz

## Autor

Proyecto realizado por: Emanuel Roberto Espinosa
Curso: Java Backend - Talento Tech (CABA)
Año: 2026