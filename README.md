
# Proyecto de Redes Unificadas - Quilla Networks

Este proyecto consiste en desarrollar una solución integral de **Comunicaciones Unificadas (CU)**, que incluye una serie de servicios diseñados para mejorar la comunicación y colaboración dentro de un entorno corporativo o de equipo.

## Servicios a Implementar

### 1. Voz y Telefonía
- Implementación de funcionalidades de llamada y gestión telefónica.

### 2. Video
- Capacidad de realizar videollamadas y videoconferencias.

### 3. Chat y Mensajería
- Plataforma para mensajería instantánea y persistente.

### 4. Presencia
- Indicadores de estado de disponibilidad de los usuarios.

### 5. Edición Compartida de Documentos
- Funcionalidad para colaborar en tiempo real sobre documentos.

## Arquitectura y Despliegue

El sistema se despliega utilizando **Docker Compose**, y cuenta con tres configuraciones distintas:

### 1. Ambiente de Producción Unipersonal
- Un archivo Docker Compose que permite ejecutar el sistema en un único servidor para pruebas o despliegue controlado.

### 2. Ambiente en Dos Servidores
- Dos archivos Docker Compose que facilitan el despliegue del sistema en un entorno distribuido, con la posibilidad de tener contenedores separados para diferentes servicios.

### 3. Automización del Despliegue
- En el futuro, se planea desarrollar un **script automatizado** que permita configurar y gestionar fácilmente el despliegue, automatizando tareas como el mapeo de las dos direcciones IP de los servidores y su configuración inicial, simplificando el proceso de puesta en marcha del sistema.

## Cómo Ejecutar el Proyecto

### 1. Clonar el repositorio:
   ```bash
   git clone https://github.com/AlpacaTaka/quillanetworks
  ```
### 2. Ejecutar en un Ambiente de Producción Unipersonal
- Para ejecutar el sistema en un ambiente de producción controlado unipersonal, sigue estos pasos:
 ####
  1. Navega al directorio correspondiente:
   ```bash
   cd docker-compose-unipersonal
   ```
  2. Levanta los contenedores de Docker con el siguiente comando:
   ```bash
   docker-compose up
   ```
  - Esto iniciará todos los servicios definidos en el archivo docker-compose.yml para una ejecución local controlada.
### 3. Ejecutar en un Ambiente Distribuido con Dos Servidores
- Para desplegar el sistema en un entorno distribuido con dos servidores, sigue estos pasos:
 ####
  1. Navega al directorio del primer servidor:
   ```bash
   cd docker-compose-servidor1
   ```
  2. Levanta los contenedores de Docker con el siguiente docker-compose upcomando:
   ```bash
   docker-compose up
   ```
  3. Luego, navega al directorio del segundo servidor:
   ```bash
   cd ../docker-compose-servidor2
   ```
  4. Levanta los contenedores para el segundo servidor:
   ```bash
   docker-compose up
   ```
  - Esto pondrá en funcionamiento el sistema distribuido utilizando los dos servidores.
### 4. Automatización Futura del Despliegue
- En el futuro, se proporcionará un script automatizado que facilitará el despliegue del sistema. Este script permitirá mapear las direcciones IP de los servidores y configurar automáticamente los servicios, simplificando todo el proceso de puesta en marcha.

## Solución para MYPE (Micro y Pequeña Empresa)
La solución que se está está enfocada en proporcionar una infraestructura de Comunicaciones Unificadas (CU) para pequeñas y medianas empresas (MYPE) usando software libre. El objetivo es ofrecer una plataforma de gestión de comunicaciones (voz, video, chat, presencia y colaboración en documentos) que sea escalable, económica y flexible.

## Licencia
Este proyecto está basado en software libre, utilizando herramientas open source como Asterisk, Jitsi Meet, faltan las otras.

Las licencias correspondientes para cada tecnología son:

- **Asterisk:** Licencia GPL

- **Jitsi Meet:** Licencia Apache 2.0




