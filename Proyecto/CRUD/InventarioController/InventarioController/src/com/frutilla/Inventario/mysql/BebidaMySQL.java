/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.frutilla.Inventario.mysql;

import com.frutilla.config.DBManager;
import com.frutilla.models.Inventario.Bebida;
import com.frutilla.models.Inventario.Producto;
import com.frutilla.models.Inventario.TipoLeche;
import com.frutilla.Inventario.dao.BebidaDAO;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BebidaMySQL implements BebidaDAO{
    @Override
    public int insertar(Bebida bebida,int idLocal) throws SQLException{
        int result=0;
        ProductoMySQL padre=new ProductoMySQL();
        padre.insertar(bebida,idLocal);
        String query="INSERT INTO Bebida (idProducto,tamanioOnz,tipo,endulzante,"
                + "tipoLeche) VALUES (?,?,?,?,?)";
        try(Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query,
                     PreparedStatement.RETURN_GENERATED_KEYS)){
            ps.setInt(1,bebida.getIdProducto());
            ps.setInt(2,bebida.getTamanioOz());
            ps.setString(3, bebida.getTipo());
            ps.setString(4, bebida.getEndulzante());
            ps.setString(5, bebida.getTieneLeche().name());
            result=ps.executeUpdate();
        }
        return result;
    }
    @Override
    public int actualizar(Bebida bebida,int idLocal) throws SQLException{
        int result=0;
        ProductoMySQL padre= new ProductoMySQL();
        padre.actualizarProducto(bebida, idLocal);
         String query = """
                UPDATE Bebida JOIN Producto ON 
                Bebida.idProducto=Producto.idProducto 
                SET Bebida.tipo=?, Bebida.tamanioOnz=?,
                Bebida.endulzante=?, Bebida.tipoLeche=?
                WHERE Producto.idProducto=? AND Producto.idLocal=?""";
        try (Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setString(1, bebida.getTipo());
            ps.setInt(2, bebida.getTamanioOz());
            ps.setString(3, bebida.getEndulzante());
            ps.setString(4, bebida.getTieneLeche().name());
            ps.setInt(5, bebida.getIdProducto());
            ps.setInt(6, idLocal);
            result=ps.executeUpdate();
        }
        return result;
    }
    @Override
    public void eliminar (int idProducto,int idLocal) throws SQLException{
        ProductoMySQL pro=new ProductoMySQL();
        pro.eliminar(idProducto, idLocal);
    }
    @Override
    public Bebida obtenerDatosBebida (int idProducto,int idLocal) throws 
            SQLException{
        ProductoMySQL pro=new ProductoMySQL();
        Producto temp=pro.obtenerProducto(idProducto, idLocal);
        Bebida beb=new Bebida(temp);
        String query="SELECT tamanioOnz,tipo,endulzante,tieneLeche"
                + "FROM Bebida,Producto WHERE "
                + "Producto.idProducto=Bebida.idProducto "
                + "AND Bebida.idProducto=? AND Producto.idLocal = ?";
        try(Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1,idProducto);
            ps.setInt(2, idLocal);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()){
                    beb.setTamanioOz(rs.getInt("tamanioOnz"));
                    beb.setTipo(rs.getString("tipo"));
                    beb.setEndulzante(rs.getString("endulzante"));
                    beb.setTieneLeche(TipoLeche.valueOf(
                            rs.getString("tieneLeche")));
                }
            }
        }
        return beb;
    }
    @Override
    public ArrayList<Bebida> obtenerTodos(int idLocal) throws SQLException{
        ArrayList<Bebida> bebidas= new ArrayList<Bebida> ();
        ProductoMySQL pro=new ProductoMySQL();
        String query="SELECT idProducto,tamanioOnz,tipo,endulzante,tieneLeche"
                + "FROM Bebida,Producto WHERE Bebida.idProducto = "
                + "Producto.idProducto AND Producto.idLocal=?";
        try(Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1, idLocal);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Bebida beb=new Bebida(
                        pro.obtenerProducto(rs.getInt("idProducto"), idLocal));
                beb.setTamanioOz(rs.getInt("tamanioOnz"));
                beb.setTipo(rs.getString("tipo"));
                beb.setEndulzante(rs.getString("endulzante"));
                beb.setTieneLeche(TipoLeche.valueOf(rs.getString("tieneLeche")));
                bebidas.add(beb);
            }
            rs.close();  
        }
        return bebidas;
    }
    
}
