# Documentación de Integración Frontend-Backend

## 1. Arquitectura del Sistema
- **Frontend**: React 18 + Axios + React Router
- **Backend**: Spring Boot 4 + Spring Security + JWT + JPA
- **Base de datos**: H2 (desarrollo) / MySQL (producción)
- **Comunicación**: API REST JSON sobre HTTP/HTTPS

## 2. Estructura de Servicios Frontend

### AuthService.js
- Manejo de autenticación JWT
- Login/logout con tokens
- Persistencia en localStorage
- Verificación de sesiones

### ProductService.js
- CRUD completo de productos
- Conexión a `/api/v1/pasteles`
- Manejo de errores HTTP
- Formateo de datos

### CartService.js
- Gestión local del carrito
- Persistencia en localStorage
- Cálculo de totales

## 3. Flujo de Autenticación JWT

1. Usuario ingresa credenciales en `/inicio-sesion`
2. Frontend envía POST a `/api/v1/auth/login`
3. Backend valida y genera token JWT
4. Frontend guarda token en localStorage
5. Token se envía en header `Authorization: Bearer <token>`
6. Backend valida token en cada petición protegida

## 4. Endpoints Principales

### Públicos:
- `GET /api/v1/pasteles` - Listar productos
- `POST /api/v1/auth/login` - Autenticación

### Protegidos (ADMIN):
- `POST /api/v1/pasteles` - Crear producto
- `PUT /api/v1/pasteles/{id}` - Actualizar producto
- `DELETE /api/v1/pasteles/{id}` - Eliminar producto

## 5. Estructura de Datos

### Producto:
```json
{
  "id": "TC001",
  "nombre": "Torta Cuadrada de Chocolate",
  "categoria": "Tortas Cuadradas",
  "precio": 45000.0,
  "descripcion": "Descripción...",
  "personalizable": true,
  "imagen": "/img/tcchocolate.webp"
}