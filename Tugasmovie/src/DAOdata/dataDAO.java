/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOdata;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import koneksi.connector;
import DAOimplement.dataimpl;
import movie.*;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER NITRO
 */
public class dataDAO implements dataimpl{
    Connection connection;
    final String select = "SELECT * FROM `movie`";
    final String insert = "INSERT INTO `movie`(`judul`, `alur`, `penokohan`, `akting`, `nilai`) VALUES (?,?,?,?,?)";
    final String update = "UPDATE movie set judul=?, alur=?, penokohan=?, akting=?, nilai=? where judul=?";
    final String delete = "DELETE from movie where judul =?";
    public dataDAO(){
        connection =connector.connection();
    }

    @Override
    public void insert(data p) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, p.getJudul());
            statement.setDouble(2, p.getAlur());
            statement.setDouble(3, p.getPenokohan());
            statement.setDouble(4, p.getAkting());
            
            double nilai = (p.getAlur()+p.getPenokohan()+p.getAkting())/3;
            statement.setDouble(5, nilai);
            
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            
            while(rs.next()){
                p.setJudul(rs.getString(1));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    

    @Override
    public void update(data p) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(update);
            statement.setString(1, p.getJudul());
            statement.setDouble(2, p.getAlur());
            statement.setDouble(3, p.getPenokohan());
            statement.setDouble(4, p.getAkting());
            
            double nilai = (p.getAlur()+p.getPenokohan()+p.getAkting())/3;
            statement.setDouble(5, nilai);
            String temp = p.getJudul();
            statement.setString(6, temp);
            
            statement.executeUpdate();       
            JOptionPane.showMessageDialog(null, "Success!");
        }catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed!");
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String p) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(delete);
            
            statement.setString(1, p);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Success!");

        }catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed!");

        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }


    @Override
    public List<data> getAll() {
        List<data>dp=null;
        try{
            dp = new ArrayList<data>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()){
                data mov = new data();
                mov.setJudul(rs.getString("Judul"));
                mov.setAlur(rs.getDouble("Alur"));
                mov.setPenokohan(rs.getDouble("Penokohan"));
                mov.setAkting(rs.getDouble("Akting"));
                mov.setNilai(rs.getDouble("Nilai"));
                dp.add(mov);
            }
        }catch (SQLException ex){
            Logger.getLogger(dataDAO.class.getName()).log(Level.SEVERE, null,ex);
        }
        return dp;
    }
}
