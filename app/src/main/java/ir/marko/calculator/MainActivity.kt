package ir.marko.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Toast
import ir.marko.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {
    private fun appendAll(newText: String) {
        if (binding.txtResult.text.isNotEmpty()) {
            binding.txtResult.text = ""
        }
        binding.txtResult.text = ""
        binding.txtOperations.append(newText)
        val viewTree: ViewTreeObserver = binding.horizontalScrollView.viewTreeObserver
        viewTree.addOnGlobalLayoutListener(
            object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    binding.horizontalScrollView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    binding.horizontalScrollView.scrollTo(binding.txtOperations.width, 0)

                }
            }
        )

    }

    private fun clickNumbers() {
        binding.btn0.setOnClickListener {
            if (binding.txtOperations.text.isNotEmpty()) {
                appendAll("0")
            }
        }
        binding.btn1.setOnClickListener {
            appendAll("1")
        }
        binding.btn2.setOnClickListener {
            appendAll("2")
        }
        binding.btn3.setOnClickListener {
            appendAll("3")
        }
        binding.btn4.setOnClickListener {
            appendAll("4")
        }
        binding.btn5.setOnClickListener {
            appendAll("5")
        }
        binding.btn6.setOnClickListener {
            appendAll("6")
        }
        binding.btn7.setOnClickListener {
            appendAll("7")
        }
        binding.btn8.setOnClickListener {
            appendAll("8")
        }
        binding.btn9.setOnClickListener {
            appendAll("9")
        }
    }

    private fun clickOperation() {

        binding.btnJam.setOnClickListener {
            if (binding.txtOperations.text.isNotEmpty() &&
                (binding.txtOperations.text.last() != '(' &&
                        binding.txtOperations.text.last() != '/' &&
                        binding.txtOperations.text.last() != '*' &&
                        binding.txtOperations.text.last() != '+' &&
                        binding.txtOperations.text.last() != '-' &&
                        binding.txtOperations.text.last() != '.' &&
                        binding.txtOperations.text.last() != '(')
            ) {
                appendAll("+")
            }
        }
        binding.btnMenha.setOnClickListener {
            if (binding.txtOperations.text.last() != '-' &&
                binding.txtOperations.text.last() != '.'
            ) {
                appendAll("-")
            } else if (binding.txtOperations.text.last() == '+') {
                val oldText = binding.txtOperations.text.toString()
                binding.txtOperations.text = oldText.substring(0, oldText.length - 1)
                appendAll("-")
            }
        }
        binding.btnZarb.setOnClickListener {
            if (binding.txtOperations.text.isNotEmpty() &&
                (binding.txtOperations.text.last() != '(' &&
                        binding.txtOperations.text.last() != '/' &&
                        binding.txtOperations.text.last() != '*' &&
                        binding.txtOperations.text.last() != '+' &&
                        binding.txtOperations.text.last() != '-' &&
                        binding.txtOperations.text.last() != '.' &&
                        binding.txtOperations.text.last() != '(')
            ) {
                appendAll("*")
            }
        }
        binding.btnTaghsim.setOnClickListener {
            if (binding.txtOperations.text.isNotEmpty() &&
                (binding.txtOperations.text.last() != '(' &&
                        binding.txtOperations.text.last() != '/' &&
                        binding.txtOperations.text.last() != '*' &&
                        binding.txtOperations.text.last() != '+' &&
                        binding.txtOperations.text.last() != '-' &&
                        binding.txtOperations.text.last() != '.' &&
                        binding.txtOperations.text.last() != '(')
            ) {
                appendAll("/")
            }
        }
        binding.btnParantezBaz.setOnClickListener {
            appendAll("(")
        }
        binding.btnParantezBaste.setOnClickListener {
            if (binding.txtOperations.text.contains('(') && (binding.txtOperations.text.last() == '1' ||
                        binding.txtOperations.text.last() == '2' ||
                        binding.txtOperations.text.last() == '3' ||
                        binding.txtOperations.text.last() == '4' ||
                        binding.txtOperations.text.last() == '5' ||
                        binding.txtOperations.text.last() == '6' ||
                        binding.txtOperations.text.last() == '7' ||
                        binding.txtOperations.text.last() == '8' ||
                        binding.txtOperations.text.last() == '9' ||
                        binding.txtOperations.text.last() == '0'
                        )
            ) {
                appendAll(")")
            }
        }
        binding.btnMosavi.setOnClickListener {
            try {
                val operationsDone =
                    ExpressionBuilder(binding.txtOperations.text.toString()).build()
                val result = operationsDone.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble() && longResult.toString().length < 14) {
                    binding.txtResult.text = "=${longResult.toString()}"
                } else  {
                    binding.txtResult.text = "=${result.toString()}"

                }
            } catch (error: Exception) {
                Toast.makeText(this, "Please Enter Valid Operation", Toast.LENGTH_LONG).show()
            }
        }
        binding.btnAshar.setOnClickListener {
            var haveDot = false
            val textCheck = binding.txtOperations.text.toString()
            val eachPart = textCheck.split('+', '-', '/', '*')
            eachPart.forEach {
                if (it.contains('.')) {
                    haveDot = true
                } else {
                    haveDot = false
                }
            }
            if ((binding.txtOperations.text.isEmpty() ||
                        binding.txtOperations.text.last() == '(' ||
                        binding.txtOperations.text.last() == ')' ||
                        binding.txtOperations.text.last() == '/' ||
                        binding.txtOperations.text.last() == '*' ||
                        binding.txtOperations.text.last() == '-' ||
                        binding.txtOperations.text.last() == '+') &&
                haveDot == false

            ) {
                appendAll("0.")
            } else if (binding.txtOperations.text.last() != '.' && haveDot == false) {
                appendAll(".")
            } else {

            }
        }
        binding.btnHazf.setOnClickListener {
            val oldText = binding.txtOperations.text.toString()
            if (oldText.isNotEmpty()) {
                binding.txtOperations.text = oldText.substring(0, oldText.length - 1)
            }
        }
        binding.btnAc.setOnClickListener {
            binding.txtOperations.text = ""
            binding.txtResult.text = ""
        }
    }

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickNumbers()
        clickOperation()


    }
}
