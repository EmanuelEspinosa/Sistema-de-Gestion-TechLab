package com.techlab.ecommerce;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.techlab.ecommerce.model.Categoria;
import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.service.CategoriaService;
import com.techlab.ecommerce.service.ProductoService;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Bean
	public CommandLineRunner cargarDatos(ProductoService service, CategoriaService serviceCtg) {
		return args -> {

			if (serviceCtg.listarCategorias().isEmpty()) {
				Categoria celular = serviceCtg
						.guardarCtg(new Categoria("celular", "Dispositivos móviles inteligentes para comunicación"));
				Categoria smartwatch = serviceCtg.guardarCtg(new Categoria("smartwatch",
						"Relojes inteligentes con funciones de monitoreo de salud y notificaciones"));
				Categoria impresora = serviceCtg.guardarCtg(new Categoria("impresora",
						"Equipos de impresión, escaneo y copiado de documentos y fotografías"));
				Categoria auricular = serviceCtg.guardarCtg(new Categoria("auricular", "Dispositivos de audio personales para escuchar música, llamadas y contenido multimedia"));

				if (service.getListaProductos().isEmpty()) {
					service.guardarProducto(new Producto("Impresora Ecotank L1250", 335.699, 5, impresora, "La impresora Epson Ecotank L1250 es compacta y ligera, diseñada para ocupar poco espacio. Con ella podrás imprimir con gran calidad cualquier documento. Además, cuenta con impresión de fotos optimizada, que mejora los tonos negros para dar excelentes texturas, sombras y contrastes en alta resolución", "Epson", "https://i.ibb.co/G4SHTXGH/a51ba38cc959.jpg"));
					service.guardarProducto(new Producto("Galaxy A16 4G", 399500, 3, celular, "El celular Samsung Galaxy A16 cuenta con una impresionante pantalla Super AMOLED de 6.7 pulgadas. Con resolución FHD+, esta pantalla te permitirá ver todo tu contenido con una calidad increíble. ", "Samsung", "https://i.ibb.co/m5pScXv5/4941eefb9b41.jpg"));
					service.guardarProducto(new Producto("Jd Capri 1.83", 40500, 3, smartwatch, "Con el Smartwatch JD, puedes monitorear tu salud las 24 horas del día. Controla tu frecuencia cardíaca, oxígeno en sangre y presión arterial, además de supervisar tu sueño, respiración y ciclo femenino. Ofrece múltiples modos deportivos como correr, caminar, bicicleta fija, ciclismo, saltar la soga, tenis, y muchos más.", "JD", "https://i.ibb.co/k68mMnKH/8b20da0585e3.png"));
					service.guardarProducto(new Producto("Gamer G435", 126000, 10, auricular, "Con una construcción ligera, estos auriculares inalámbricos para gaming solo pesan 165 gr, por lo que son cómodos para utilizar durante todo el día. Cuentan con una calidad de voz superior, gracias a los micrófonos duales integrados que reducen el ruido de fondo.", "Logitech G", "https://i.ibb.co/k6chhKHG/7607a6bdfd5e.png"));
					service.guardarProducto(new Producto("TCL 50 Space Gray", 921399, 2, celular, "El modo de carga inteligente durante la noche, junto con la aplicación Smart Manager, mantendrá la salud de la batería y prolongará su vida útil. Batería que no te genera estrés,haz lo que tengas que hacer. Ahora, por mucho más tiempo y cárgalo al día siguiente.Carga rápida 18W.", "TCL", "https://i.ibb.co/YBXhR2sP/62e7607041c9.png"));
				}

			}

		};
	}

}
