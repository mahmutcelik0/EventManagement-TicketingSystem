//
//  LogInViewController.swift
//  Biletimss
//
//  Created by Ece Ok, Vodafone on 16.12.2023.
//

import UIKit

class LogInViewController: UIViewController {

    @IBOutlet weak var logBtn: UIButton!
    @IBOutlet weak var passw: UITextField!
    @IBOutlet weak var eMail: UITextField!

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    @IBAction func loginButtonPressed(_ sender: Any) {
        guard let email = eMail.text, let password = passw.text else {
            return
        }

        let urlString = "https://yourbackend.com/login" // Backend URL'sini buraya ekleyin
        if let url = URL(string: urlString) {
            var request = URLRequest(url: url)
            request.httpMethod = "POST"
            
            let params: [String: Any] = [
                "email": email,
                "password": password
            ]

            do {
                request.httpBody = try JSONSerialization.data(withJSONObject: params)
            } catch {
                print("JSON dönüşüm hatası: \(error)")
                return
            }

            let task = URLSession.shared.dataTask(with: request) { data, response, error in
                if let error = error {
                    print("Hata : \(error)")
                    return
                }

                if let data = data {
                    do {
                        if let json = try JSONSerialization.jsonObject(with: data, options: []) as? [String: Any] {
                            if let success = json["success"] as? Bool, success {
                                if let userInfo = json["userInfo"] as? [String: Any] {
                                    UserDefaults.standard.set(email, forKey: "userEmail")
                            
                                }
                            } else {
                                print("Kullanıcı girişi başarısız")
                            }
                        }
                    } catch {
                        print("JSON dönüşüm hatası: \(error)")
                    }
                }
            }
            task.resume()
        }
    }
}
