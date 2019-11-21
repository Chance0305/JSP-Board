package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.BoardVO;

public class BoardDAO {
	private static BoardDAO instance = new BoardDAO();

	private BoardDAO() {
	}

	public static BoardDAO getIns() {
		return instance;
	}

	// database connection method
	private Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String cString = "jdbc:mysql://gondr.asuscomm.com/twins200202?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Seoul";
			String id = "twins200202";
			String password = "1234";
			conn = DriverManager.getConnection(cString, id, password);
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Not Found");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB Connection Failed");
		}
		return conn;
	}

	// article posting method
	public int write(BoardVO data) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String sql = "INSERT INTO boards (title, content, writer, files) VALUES(?, ?, ?, ?)";
			conn = getConnection();
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, data.getTitle());
			ps.setString(2, data.getContent());
			ps.setString(3, data.getWriter());
			ps.setString(4, data.getFiles()); // 파일은 일단 공백으로

			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			try { if (ps != null) ps.close(); } catch (SQLException e) {}
			try { if (conn != null) conn.close(); } catch (SQLException e) {}
		}
	}
	
	public BoardVO view(int id) {
		String sql = "SELECT * FROM boards WHERE id = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
	
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				BoardVO data = new BoardVO();
				data.setId(rs.getInt("id"));
				data.setTitle(rs.getString("title"));
				data.setContent(rs.getString("content"));
				data.setWriter(rs.getString("writer"));
				data.setFiles(rs.getString("files"));
				System.out.println(data);
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (ps != null) ps.close(); } catch (SQLException e) {}
			try { if (rs != null) rs.close(); } catch (SQLException e) {}
			try { if (conn != null)	conn.close(); } catch (SQLException e) {}
		}
		
		return null;
	}

	public List<BoardVO> getList(int page) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		List<BoardVO> list = new ArrayList<BoardVO>();

		try {
			String sql = "SELECT * FROM boards ORDER BY id DESC LIMIT ?, 10";
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page - 1) * 10);
			rs = ps.executeQuery();

			while (rs.next()) {
				BoardVO temp = new BoardVO();
				temp.setId(rs.getInt("id"));
				temp.setTitle(rs.getString("title"));
				temp.setWriter(rs.getString("writer"));
				list.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (ps != null) ps.close(); } catch (SQLException e) {}
			try { if (rs != null) rs.close(); } catch (SQLException e) {}
			try { if (conn != null) conn.close(); } catch (SQLException e) {}
		}
		
		return list;
	}
	
	// this method brings board list
	public BoardVO selectBoard(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT id, title, content, writer, files FROM boards WHERE id=?";
        try {
            conn = this.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()) {
            	BoardVO temp = new BoardVO();
				temp.setId(rs.getInt("id"));
				temp.setTitle(rs.getString("title"));
				temp.setContent(rs.getString("content"));
				temp.setWriter(rs.getString("writer"));
				temp.setFiles(rs.getString("files"));
				
				return temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			try { if (ps != null) ps.close(); } catch (SQLException e) {}
			try { if (rs != null) rs.close(); } catch (SQLException e) {}
			try { if (conn != null)	conn.close(); } catch (SQLException e) {}
		}
		return null;
	}
	
	// this method modify articles
	public int modify(BoardVO board) {
        int rowCount = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE boards SET title=?, content=?, files=? WHERE id=?";
        try {
            conn = this.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.setString(3, board.getFiles());
            ps.setInt(4, board.getId());
            rowCount = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			try { if (ps != null) ps.close(); } catch (SQLException e) {}
			try { if (conn != null)	conn.close(); } catch (SQLException e) {}
		}
        return rowCount;
    }
}
