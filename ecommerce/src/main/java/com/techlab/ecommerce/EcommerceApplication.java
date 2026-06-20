package com.techlab.ecommerce;

// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;

// import com.techlab.ecommerce.model.Producto;
// import com.techlab.ecommerce.service.ProductoService;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	// @Bean
	// public CommandLineRunner cargarDatos(ProductoService service){
	// 	return args -> {
	// 		service.guardarProducto(new Producto("Redmi Note 10", 250000, 5, "Celular", "Xiaomi"));
    //     	service.guardarProducto(new Producto("Galaxy A16 4G", 399500,3, "Celular", "Samsung"));
    //     	service.guardarProducto(new Producto("Jd Capri 1.83", 40500,3, "Smartwatch", "JD"));
    //     	service.guardarProducto(new Producto("Gamer G435", 126000,10, "Auriculares", "Logitech G"));
    // 		service.guardarProducto(new Producto("Ecotank L1250", 335699,2, "Impresora", "Epson"));
	// 	};
	// }

}
