/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOimplement;
import java.util.List;
import movie.*;

/**
 *
 * @author ACER NITRO
 */
public interface dataimpl {
    public void insert(data p);
    public void update(data p);
    public void delete(String judul);
    public List<data>getAll();
    
}
