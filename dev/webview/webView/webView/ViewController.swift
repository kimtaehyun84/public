//
//  ViewController.swift
//  webView
//
//  Created by 김태현 on 2019. 7. 31..
//  Copyright © 2019년 김태현. All rights reserved.
//

import UIKit
import WebKit


class ViewController: UIViewController, WKUIDelegate, WKNavigationDelegate{

    @IBOutlet weak var webView: WKWebView!
    
    override func loadView(){
        super.loadView();
        webView = WKWebView(frame : self.view.frame)
        webView.uiDelegate = self
        webView.navigationDelegate = self
        
        self.view = self.webView
    }
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        let url = URL(string : "http://150.30.5.74:18080/fcr");
        let request = URLRequest(url:url!);
        webView.load(request);
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    func webView(_ webView: WKWebView, runJavaScriptAlertPanelWithMessage message: String, initiatedByFrame frame : WKFrameInfo, completionHandler: @escaping () -> Void){
        let alertController = UIAlertController(title: "", message: message, preferredStyle:.alert)
        alertController.addAction(UIAlertAction(title:"Confirm", style: .default, handler:{
            (action) in completionHandler()
        }))
        
        self.present(alertController, animated: true, completion: nil)
    }
    
    func webView(_ webView: WKWebView, runJavaScriptConfirmPanelWithMessage message: String, initiatedByFrame frame : WKFrameInfo, completionHandler: @escaping (Bool) -> Void){
        let alertController = UIAlertController(title: "", message: message, preferredStyle:.alert)
        alertController.addAction(UIAlertAction(title:"Confirm", style: .default, handler:{
            (action) in completionHandler(true)
        }))
        self.present(alertController, animated: true, completion: nil)
    }
    
    func webView(_ webView: WKWebView, runJavaScriptConfirmPanelWithPrompt prompt: String, defaultText: String?, initiatedByFrame frame : WKFrameInfo, completionHandler: @escaping (String?) -> Void){
        let alertController = UIAlertController(title: "", message: prompt, preferredStyle:.alert)
        alertController.addTextField {(textField) in textField.text = defaultText}
        alertController.addAction(UIAlertAction(title:"Confirm", style:.default, handler:{
            (action) in
            if let text = alertController.textFields?.first?.text{
                completionHandler(text)
            }
            else{
                completionHandler(defaultText)
                
            }
        }))
        alertController.addAction(UIAlertAction(title:"cancel", style:.default, handler: {
            (action) in completionHandler(nil)
        }))
        
        self.present(alertController, animated: true, completion: nil)
    }
    
    func webView(_ webView: WKWebView, createWebViewWith configuration: WKWebViewConfiguration, for navigationAction: WKNavigationAction, windowFeatures: WKWindowFeatures) -> WKWebView? {
        if navigationAction.targetFrame == nil {
            webView.load(navigationAction.request)
        }
        return nil
    }
    
    public func webViewWebContentProcessDidTerminate(_ webView: WKWebView){
        webView.reload()
    }
    
//    public func gedDeviceId(){
//
//        KeychainItemWrapper *wrapper = [[KeychainItemWrapper alloc]] initWithIdentifier:@"UUID" accessGroup:nil];
//        NSString *uuid = [wrapper objectForKey:(_bridge id)(kSecAttrAccount)];
//
//        if(uuid == nil || uuid.length == 0 ){
//            CFUUIDRef uuidRef = CFUUIDCreate(NULL);
//            CFStringRef uuidStringRef = CFUUIDCreateString(NULL, uuidRef);
//            CFRelease(uuidRef);
//            uuid = [NSString stringWithString:(_bridge NSString *) uudiStringRef];
//            CFRelease(uuidStringRef);
//
//            [wrapper setObject:uuid forKey:(_bridge id)(kSecAttrAccount)];
//        }
//        webView.load("javascript:mapp.callbackJavascriptInterface('"+uuid+"')");
//    }
    

}

