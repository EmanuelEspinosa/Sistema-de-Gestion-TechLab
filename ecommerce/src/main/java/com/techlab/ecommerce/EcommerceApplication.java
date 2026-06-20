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
					service.guardarProducto(new Producto("Redmi Note 10", 250000, 5, celular, "Xiaomi"));
					service.guardarProducto(new Producto("Galaxy A16 4G", 399500, 3, celular, "Samsung"));
					service.guardarProducto(new Producto("Jd Capri 1.83", 40500, 3, smartwatch, "JD"));
					service.guardarProducto(new Producto("Gamer G435", 126000, 10, auricular, "Logitech G"));
					service.guardarProducto(new Producto("Ecotank L1250", 335699, 2, impresora, "Epson"));
				}

			}

		};
	}

}
