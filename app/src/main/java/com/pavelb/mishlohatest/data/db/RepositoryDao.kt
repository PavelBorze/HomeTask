package com.pavelb.mishlohatest.data.db
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pavelb.mishlohatest.data.entities.Repository

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repository: Repository)

    @Query("SELECT * FROM repository")
    fun getAll(): PagingSource<Int, Repository>

    @Query("DELETE FROM repository")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) > 0 FROM repository WHERE id = :id")
    suspend fun exists(id: String): Boolean
}