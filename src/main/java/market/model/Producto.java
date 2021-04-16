package market.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Producto {
    private Integer idProducto;
    private String nombre;
    private Integer idCategoria;
    private String codigoBarras;
    private Double precioVenta;
    private Integer cantidadStock;
    private Boolean estado;
}
