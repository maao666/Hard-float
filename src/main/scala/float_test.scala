package hardfloat

import chisel3._

import chisel3._
import chisel3.util._
import chisel3.iotesters._
import org.scalatest.{Matchers, FlatSpec}
import scala.util.Random
import java.math._
import scala.math._

import hardfloat._

class FloatPeekPokeTester(c: Equiv_MulAddFN) extends PeekPokeTester(c) {
    poke(c.io.a, 1105513677) // 28.6
    poke(c.io.b, 1118594662) // 86.2
    poke(c.io.c, 1115370291) // 62.8
    poke(c.io.op, 0)
    poke(c.io.roundingMode, 0)
    poke(c.io.detectTininess, 0)

    val result = peek(c.io.out)
    println(s"$result")
}


object FloatTest extends App {
    chisel3.iotesters.Driver.execute(Array("--backend-name", "verilator"), () => new Equiv_MulAddFN(8, 24)) { c =>
    	new FloatPeekPokeTester(c)
    }
}
