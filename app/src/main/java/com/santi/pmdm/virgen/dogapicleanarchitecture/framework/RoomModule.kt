package com.santi.pmdm.virgen.dogapicleanarchitecture.framework

import android.content.Context
import androidx.room.Room
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.DatabaseDogs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
Módulo que nos inyecta la base de datos.

1.- Queremos que nuestro módulo sea un síngleton. Lo creará Hilt y las dependencias que genere a otras clases, serán síngleton. Para ello,  el módulo debe ser un OBJECT.
Con @InstallIn(SingletonComponent::class), estamos diciendo que este componente que crea Hilt, vivirá todo el ciclo de vida de la app y sus dependencias también.
2.- Con @Singleton en el método provideRoom, estamos diciendo que la instancia que devuelve dicho método, será un
singletón y por tanto, durará todo el tiempo. Es lo mismo que con el módulo.
todo el tiempo en el que la aplicación esté funcionando, hagamos lo que hagamos.
4.- Con @Provides, porque le indicamos a Hilt, que el método va a proveer una dependencia que será la conexión
con la base de datos y la podrá solicitar/inyectar cualquier otra clase. Si no le ponemos Provides,
Hilt no controlará la dependencia a otras clases, cuando éstas la requieran.
5.- Necesitamos otro método, que Hilt nos provea del objeto Dao que necesitaremos para realizar nuestras operaciones Dao. Para ello,
será un singleton y un provide. El método aceptará el objeto ya inyectado por Hilt de tipo DatabaseDogs y devolverá el database.dogDao,
que es donde tendremos los métodos dao con la BBDD. Hilt creará el objreto database. Una pregunta que podemos hacernos es.
Si el parámetro database lo crea Hilt, porqué no le ponemos la anotación @Inyect? La respuesta es por el funcioamiento de la anotación
@Provide. Hile con @Provide, entiende que database:DatabaseDogs, también lo tiene que inyectar él, por tanto lo creará automáticamente.
Por otra parte, @Inyect se utiliza en constructores....
*/


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val NAME_DATABASE = "database_dogs"

    //Nos devuelve una instancia de Room
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DatabaseDogs::class.java, NAME_DATABASE).build()


    //Nos devuelve una instancia de la Base de datos.
    @Singleton
    @Provides
    fun provideDao(database: DatabaseDogs) = database.dogDao()


}