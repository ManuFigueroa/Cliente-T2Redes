En JavaWebServer (cliente TCP) habrá que cambiar la variable global "host" según 
dónde se esté ejecutando el servidor.
Si se quieren ejecutar dos clientes en un mismo ordenador será necesario cambiar
la variable "puerto" (que se encuentra inicializada en 8081) por cualquier otra (menos 8080)
lo que creará un nuevo servidor HTTP en otro puerto.