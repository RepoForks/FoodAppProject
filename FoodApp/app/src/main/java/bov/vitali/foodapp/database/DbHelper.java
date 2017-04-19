package bov.vitali.foodapp.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import bov.vitali.foodapp.models.Category;
import bov.vitali.foodapp.models.Offer;

/**
 * Created by vitali on 19.4.17.
 */

public class DbHelper extends OrmLiteSqliteOpenHelper{

    private static final String DATABASE_NAME = "food_catalog";
    private static final int DATABASE_VERSION = 1;

    private Dao<Category, Integer> categoryDao;
    private Dao<Offer, Integer> offerDao;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Category.class);
            TableUtils.createTable(connectionSource, Offer.class);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
    }

    public Dao<Category, Integer> getCategoryDao() throws SQLException, java.sql.SQLException {
        if (categoryDao == null)
            categoryDao = getDao(Category.class);
        return categoryDao;
    }

    public Dao<Offer, Integer> getOfferDao() throws SQLException, java.sql.SQLException {
        if (offerDao == null)
            offerDao = getDao(Offer.class);
        return offerDao;
    }
}
