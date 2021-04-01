package ar.edu.unahur.obj2.vendedores

class CentroDeDistribucion (val ciudad: Ciudad){

    val vendedoresDelCentro = mutableListOf<Vendedor>()

    fun agregarVendedor(Vendedor : Vendedor) {

        if (vendedoresDelCentro.contains(Vendedor)) {
            throw Exception("este vendedor ya se encuentra en la lista")
        }else{
            vendedoresDelCentro.add(Vendedor)
        }
    }


    fun vendedorEstrella() = vendedoresDelCentro.maxBy{it.puntajeCertificaciones()}

    fun puedeCubrirCiudad(ciudad: Ciudad) = vendedoresDelCentro.any{it.puedeTrabajarEn(ciudad)}

    fun vendedoresGenericosRegistrados() = vendedoresDelCentro.filter{it.esGenerico()}

    fun esRobusto() = vendedoresDelCentro.filter{ it.esFirme()}.size >= 3



}