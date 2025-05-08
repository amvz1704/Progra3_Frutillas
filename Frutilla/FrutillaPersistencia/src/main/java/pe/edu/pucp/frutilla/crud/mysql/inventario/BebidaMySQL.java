/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.crud.mysql.inventario;

import pe.edu.pucp.frutilla.config.DBManager;
import pe.edu.pucp.frutilla.models.inventario.Bebida;
import pe.edu.pucp.frutilla.models.inventario.Producto;
import pe.edu.pucp.frutilla.models.inventario.TipoLeche;
import pe.edu.pucp.frutilla.crud.dao.inventario.BebidaDAO;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class BebidaMySQL implements BebidaDAO{
    @Override
    public void insertarBebida(Bebida bebida) throws SQLException{
        int id = 0;
        ProductoMySQL padre=new ProductoMySQL();
        id=padre.insertarProductoDevolverID(bebida);
        bebida.setIdProducto(id);
        String query="INSERT INTO Bebida (idProducto,tamanioOnz,tipo,endulzante,"
                + "tipoLeche) VALUES (?,?,?,?,?)";
        try(Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps=con.prepareStatement(query,
                     PreparedStatement.RETURN_GENERATED_KEYS)){
            ps.setInt(1,bebida.getIdProducto());
            ps.setInt(2,bebida.getTamanioOz());
            ps.setString(3, bebida.getTipo());
            ps.setString(4, bebida.getEndulzante());
            ps.setString(5, bebida.getTieneLeche().name());
            ps.executeUpdate();
        }
    }
    @Override
    public void actualizarBebida(Bebida bebida) throws SQLException{
        ProductoMySQL padre= new ProductoMySQL();
        padre.actualizarProducto(bebida);
         String query = """
                UPDATE Bebida JOIN Producto ON 
                Bebida.idProducto=Producto.idProducto 
                SET Bebida.tipo=?, Bebida.tamanioOnz=?,
                Bebida.endulzante=?, Bebida.tipoLeche=?
                WHERE Producto.idProducto=?""";
        try (Connection con=DBManager.getInstance().getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setString(1, bebida.getTipo());
            ps.setInt(2, bebida.getTamanioOz());
            ps.setString(3, bebida.getEndulzante());
            ps.setString(4, bebida.getTieneLeche().name());
            ps.setInt(5, bebida.getIdProducto());
            ps.executeUpdate();
        }
    }
    @Override
    public void eliminarBebida (int idProducto,int idLocal) throws SQLException{
        ProductoMySQL pro=new ProductoMySQL();
        pro.eliminarProducto(idProducto, idLocal);
    }
    @Override
    public Bebida obtenerBebidaPorId (int idProducto) throws 
            SQLException{
        ProductoMySQL pro=new ProductoMySQL();
        Producto temp=pro.obtenerProductoPorId(idProducto);
        Bebida beb=new Bebida(temp);
        String query="SELECT tamanioOnz, tipo, endulzante, tipoLeche FROM Bebida,"
                + "Producto WHERE Producto.idProducto=Bebida.idProducto "
                + "AND Bebida.idProducto=?";
        try(Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1,idProducto);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()){
                    beb.setTamanioOz(rs.getInt("tamanioOnz"));
                    beb.setTipo(rs.getString("tipo"));
                    beb.setEndulzante(rs.getString("endulzante"));
                    beb.setTieneLeche(TipoLeche.valueOf(
                            rs.getString("tipoLeche")));
                }
            }
        }
        return beb;
    }
    @Override
    public ArrayList<Bebida> obtenerTodos(int idLocal) throws SQLException{
        ArrayList<Bebida> bebidas= new ArrayList<Bebida> ();
        String query="SELECT Bebida.idProducto FROM Bebida,Inventario WHERE "
                + "Bebida.idProducto = Inventario.idProducto AND "
                + "Inventario.idLocal=?";
        try(Connection con=DBManager.getInstance().getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1, idLocal);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()){
                    Bebida beb=obtenerBebidaPorId(rs.getInt("idProducto"));
                    bebidas.add(beb);
                }
            }
        }
        return bebidas;
    }
    
}
