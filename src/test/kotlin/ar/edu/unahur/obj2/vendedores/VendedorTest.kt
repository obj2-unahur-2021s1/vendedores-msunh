package ar.edu.unahur.obj2.vendedores

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class VendedorTest : DescribeSpec({

  //provincias
  val misiones = Provincia(1300000)
  val buenosAires = Provincia(15771581)
  val mendoza = Provincia(1000000)
  val sanLuis = Provincia(4552200)
  val laPampa = Provincia(500000)
  val sanJuan = Provincia(600000)
  //ciudades
  val sanIgnacio = Ciudad(misiones)
  val caucete = Ciudad(misiones)
  val moron = Ciudad(buenosAires)
  val godoyCruz = Ciudad(mendoza)
  val guaymallen = Ciudad(mendoza)
  val junin = Ciudad(mendoza)
  val maipu = Ciudad(mendoza)
  val sanRafael = Ciudad(mendoza)
  val rivadavia = Ciudad(mendoza)
  val chacabuco = Ciudad(sanLuis)
  val coronelPringles = Ciudad(sanLuis)
  val rancul = Ciudad(laPampa)

  //fijo
  describe("Vendedor fijo") {
    val obera = Ciudad(misiones)
    val vendedorFijo = VendedorFijo(obera)

    describe("puedeTrabajarEn") {
      it("su ciudad de origen") {
        vendedorFijo.puedeTrabajarEn(obera).shouldBeTrue()
      }
      it("otra ciudad") {
        vendedorFijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
    }

    describe ("no es influyente"){
      it("ningun vendedor fijo es influyente"){
        vendedorFijo.esInfluyente().shouldBeFalse()
      }
    }
  }

  //viajante
  describe("Viajante") {
    val cordoba = Provincia(2000000)
    val villaDolores = Ciudad(cordoba)
    val viajante = Viajante(listOf(misiones))

    describe("puedeTrabajarEn") {
      it("una ciudad que pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
      }
      it("una ciudad que no pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(villaDolores).shouldBeFalse()
      }
    }

    describe("no es influyente") {
      it("no es influyente") {
        viajante.esInfluyente().shouldBeFalse()
      }
    }
  }

  describe ("viajante influyente"){
      val viajanteInfluyente = Viajante(listOf(buenosAires))

    describe("es influyente"){
      it("es influyente"){
        viajanteInfluyente.esInfluyente().shouldBeTrue()
      }
    }
  }

  //corresponsal
  describe(" Primer ComercioCorresposal"){
    val listaDeCiudades = mutableListOf<Ciudad>()

    listaDeCiudades.add(godoyCruz)
    listaDeCiudades.add(guaymallen)
    listaDeCiudades.add(sanRafael)
    listaDeCiudades.add(maipu)

    val primerComercioCorresponsal = ComercioCorresponsal(listaDeCiudades)

    it ("no es influyente"){
      primerComercioCorresponsal.esInfluyente().shouldBeFalse()
    }

  }

  describe(" Segundo ComercioCorresposal"){
    val listaDeCiudades = mutableListOf<Ciudad>()

    listaDeCiudades.add(godoyCruz)
    listaDeCiudades.add(guaymallen)
    listaDeCiudades.add(sanRafael)
    listaDeCiudades.add(maipu)
    listaDeCiudades.add(junin)

    val segundoComercioCorresponsal = ComercioCorresponsal(listaDeCiudades)

    it ("es influyente"){
      segundoComercioCorresponsal.esInfluyente().shouldBeTrue()
    }

  }

  describe(" Tercer ComercioCorresposal"){
    val listaDeCiudades = mutableListOf<Ciudad>()

    //3 provincias - 4 ciudades
    listaDeCiudades.add(godoyCruz)
    listaDeCiudades.add(chacabuco)
    listaDeCiudades.add(coronelPringles)
    listaDeCiudades.add(rancul)


    val tercerComercioCorresponsal = ComercioCorresponsal(listaDeCiudades)

    it ("es influyente"){
      tercerComercioCorresponsal.esInfluyente().shouldBeTrue()
    }

  }




})
