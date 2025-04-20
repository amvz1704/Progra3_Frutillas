/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.frutilla.Inventario.mysql;

import com.frutilla.config.DBManager;
import com.frutilla.models.Inventario.Bebida;
import com.frutilla.models.Inventario.Producto;
import com.frutilla.models.Inventario.FrutasBebida;
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
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1,bebida.getIdProducto());
            //niguno de estos constructores estan en implementados
            //ps.setInt(2,bebida.getTamanioOnz());
            //ps.setString(3, bebida.getTipo());
            //ps.setString(4, bebida.getEndulzante());
            //ps.setString(5, bebida.getTipoLeche());
            //ver bien como se va considerar las frutas que estan en la bebida
            result=ps.executeUpdate();
        }
        return result;
    }
    @Override
    public int actualizar(FrutasBebida f) throws SQLException{
        //no estoy segura como se va a guardar en la bd por eso no se implementa
        int result=0;
        return result;
    }
    @Override
    public Bebida obtenerDatosBebida (int idProducto,int idLocal) throws 
            SQLException{
        Producto temp=new Producto();
        ProductoMySQL pro=new ProductoMySQL();
        temp=pro.obtenerProducto(idProducto, idLocal);
        //necesitamos implementar un constructor con parametros de Productos
        Bebida beb=new Bebida(/*temp.getIdProducto(),temp.getNombre(),
        temp.getDescripcion(),temp.getCodigoProd(),temp.getStock(),
        temp.getStockMinimo()*/);
        //falta implementar las cosas de arra de frutas de bebidas
        String query="SELECT tamanioOnz,tipo,endulzante,tieneLeche"
                + "FROM Bebida,Producto WHERE "
                + "Producto.idProducto=Bebida.idProducto AND Bebida.idProducto=?"
                + " AND Producto.idLocal = ?";
        try(Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1,idProducto);
            ps.setInt(2, idLocal);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                //beb.setTamanioOnz(rs.getInt("tamanioOnz"));
                //beb.setTipo(rs.getString("tipo"));
                //beb.setEndulzante(rs.getString("endulzante"));
                //beb.setTieneLeche(rs.getString(TipoLeche.valueOf("tieneLeche")));
            }
            rs.close();  
        }
        return beb;
    }
    @Override
    public ArrayList<Bebida> obtenerTodos(int idLocal) throws SQLException{
        ArrayList<Bebida> bebidas= new ArrayList<Bebida> ();
        ProductoMySQL pro=new ProductoMySQL();
        //necesitamos implementar un constructor con parametros de Producto
        //falta implementar las cosas de arra de frutas de bebidas
        String query="SELECT idProducto,tamanioOnz,tipo,endulzante,tieneLeche"
                + "FROM Bebida,Producto WHERE Bebida.idProducto = "
                + "Producto.idProducto AND Producto.idLocal = ?";
        try(Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1, idLocal);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Bebida beb=new Bebida(/*pro.obtenerProducto
                (rs.getInt("idProducto"), idLocal);*/);
                beb.setIdProducto(rs.getInt("idProducto"));
//                beb.setTamanioOnz(rs.getInt("tamanioOnz"));
//                beb.setTipo(rs.getString("tipo"));
//                beb.setEndulzante(rs.getString("endulzante"));
//                beb.setTieneLeche(TipoLeche.valueOf(rs.getString("tieneLeche")));
                bebidas.add(beb);
            }
            rs.close();  
        }
        return bebidas;
    }
    
}
