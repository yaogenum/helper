package mybatis.test;

import java.util.HashMap;
import java.util.List;

public interface Db_userinforDao{
	
	public void insert(Db_userinfor userinfor);
	public List<Db_userinfor> selectall(HashMap map);
//	public List<Db_userinfor> selectall(HashMap map);
	public List<HashMap> selectlist();
	public Db_userinfor selectmap();
	public List<UnionQuery> selecttwo();
	public void deleteobject(String username);
}   
   /* public int update(Db_userinfor userinfor);
    
    public int delete(String username);
    
    public List<Db_userinfor> selectAll();
    
    public int countAll();
    
    public Db_userinfor[] findByusername(String username); */

