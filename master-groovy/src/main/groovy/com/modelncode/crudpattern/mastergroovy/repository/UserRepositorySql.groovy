package com.modelncode.crudpattern.mastergroovy.repository

class UserRepositorySql {
    public static final String 목록조회 = """
		SELECT * FROM MC_USER
	"""

    public static final String 조회 = """
		SELECT * FROM MC_USER where id=?
	"""

    public static final String 추가 = """
		INSERT INTO MC_USER(name,alias,email) VALUES (?,?,?)
	"""

    public static final String 수정 = """
		UPDATE MC_USER SET name=?, alias=?, email=? where id=?
	"""

    public static final String 삭제 = """
		DELETE from MC_USER WHERE id=?
	"""
}
