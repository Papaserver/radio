package com.codecool.database;


import java.sql.*;

public class RadioCharts {

	String url;
	String user;
	String password;

	public RadioCharts(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public String getMostPlayedSong() {
		String song = "";
		try (Connection con = DriverManager.getConnection(url, user, password)) {
			String sql = "select song from music_broadcast " +
					"group by song, times_aired " +
					"order by times_aired DESC " +
					"limit 1";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				return (rs.getString("song"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return song;
	}

	public String getMostActiveArtist() {
		String artist = "";
		try (Connection con = DriverManager.getConnection(url, user, password)) {
			String sql = "select artist, count(distinct song) from music_broadcast " +
					"group by artist " +
					"order by count(distinct song) desc " +
					"limit 1";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				return (rs.getString("artist"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return artist;
	}
}
