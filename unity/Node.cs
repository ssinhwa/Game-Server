using System.Collections;
using System.Collections.Generic;
using UnityEngine;


using WebSocketSharp;
public class Node : MonoBehaviour
{

    private WebSocket webSocket;

    // Start is called before the first frame update
    void Start()
    {
        webSocket = new WebSocket("ws://localhost:8080/ws/stomp");
        webSocket.OnMessage += ws_OnMessage;
        webSocket.OnOpen += ws_OnOpen;
        webSocket.OnClose += ws_OnClose;
        webSocket.Connect();
        webSocket.Send("hello");
    }

    void ws_OnMessage(object sender, MessageEventArgs e)
    {
        Debug.Log(e.Data);//받은 메세지를 디버그 콘솔에 출력한다.
    }
    void ws_OnOpen(object sender, System.EventArgs e)
    {
        Debug.Log("open"); //디버그 콘솔에 "open"이라고 찍는다.
    }
    void ws_OnClose(object sender, CloseEventArgs e)
    {
        Debug.Log("close"); //디버그 콘솔에 "close"이라고 찍는다.
    }



    // Update is called once per frame
    void Update()
    {

    }
}
