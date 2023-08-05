import SwiftUI
import shared

struct ContentView: View {
    var body: some View{
        CalScreen()
    }
}


struct OperationButton: View {
    let operatorSymbol: String
    let action: () -> Void

    var body: some View {
        Button(action: action, label: {
            Text(operatorSymbol)
                .font(.system(size: 30, weight: .bold))
        })
        .frame(width: 70, height: 70)
        .foregroundColor(.black)
        .background(Color.orange)
        .clipShape(Circle())
        
    }
}

struct CalScreen:View{
    @State private var num1 = ""
    @State private var num2 = ""
    @State private var operatorSymbol = ""
    @State private var result = ""
    var body: some View{
        VStack(alignment: .leading){
            Text(result)
                .font(.system(size: 50, weight: .bold))
                .foregroundColor(.white)
            
            VStack(alignment: .trailing){
                Text(num1)
                    .font(.system(size: 30, weight: .bold))
                    .foregroundColor(.white)
                
                Text(num2)
                    .font(.system(size: 30, weight: .bold))
                    .foregroundColor(.white)
                   
            }
            .frame(maxWidth: .infinity, alignment:.trailing)
            .padding()
            HStack{
                VStack{
                    ForEach(0..<3) { rowIndex in
                        HStack(spacing: 16) {
                            ForEach(1..<4) { digit in
                                let number = rowIndex * 3 + digit
                                Button(action: {
                                    if operatorSymbol.isEmpty {
                                        num1 += String(number)
                                    } else {
                                        num2 += String(number)
                                    }
                                }, label: {
                                    Text(String(number))
                                        .font(.system(size: 20, weight: .bold))
                                })
                                .frame(width: 70, height: 70)
                                .background(Color.gray)
                                .foregroundColor(Color.white)
                                .clipShape(Circle())
                              
                            }
                        }
                    }
                    HStack(spacing: 16.5){
                        Spacer()
                        Button(action: {
                            if operatorSymbol.isEmpty {
                                num1 += "0"
                            } else {
                                num2 += "0"
                            }
                        }, label: {
                            Text("0")
                                .font(.system(size: 20, weight: .bold))
                        })
                        .frame(width: 70, height: 70)
                        .background(Color.gray)
                        .foregroundColor(Color.white)
                        .clipShape(Circle())

                        Button(action: {
                            if operatorSymbol.isEmpty {
                                num1 += "."
                            } else {
                                num2 += "."
                            }
                        }, label: {
                            Text(".")
                                .font(.system(size: 20, weight: .bold))
                        })
                        .frame(width: 70, height: 70)
                        .background(Color.gray)
                        .foregroundColor(Color.white)
                        .clipShape(Circle())

                        Button(action: {
                            num1 = ""
                            num2 = ""
                            operatorSymbol = ""
                            result = ""
                        }, label: {
                            Text("C")
                                .font(.system(size: 20, weight: .bold))
                                
                        })
                        .frame(width: 70, height: 70)
                        .background(Color.orange)
                        .foregroundColor(Color.white)
                        .clipShape(Circle())
                        Spacer()
                    }
                }
                
                
                VStack(alignment: .trailing) {
                    OperationButton(operatorSymbol: "+") { operatorSymbol = "+" }
                    OperationButton(operatorSymbol: "-") { operatorSymbol = "-" }
                    OperationButton(operatorSymbol: "x") { operatorSymbol = "*" }
                    OperationButton(operatorSymbol: "/") { operatorSymbol = "/" }
                }
                


            }
            
           
            Button(action: {
                if !num1.isEmpty && !num2.isEmpty && !operatorSymbol.isEmpty {
                    let n1 = Double(num1) ?? 0
                    let n2 = Double(num2) ?? 0
                    let calculator = Calculator()
                    switch operatorSymbol {
                    case "+":
                        result = String(calculator.add(a: n1, b: n2))
                    case "-":
                        result = String(calculator.subtract(a: n1, b: n2))
                    case "*":
                        result = String(calculator.multiply(a: n1, b: n2))
                    case "/":
                        result = String(calculator.divide(a: n1, b: n2))
                    default:
                        result = ""
                    }
                }
            }, label: {
                Text("=")
                    .font(.system(size: 20))
                    .frame(width: 320, height: 60)
                    .foregroundColor(.white)
                    .background(Color.orange)
                    .cornerRadius(24)
                    .padding(.leading, 35)
                
            })
            .padding(.top, 20)
        }.frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .leading).padding()
            .background(.black)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

