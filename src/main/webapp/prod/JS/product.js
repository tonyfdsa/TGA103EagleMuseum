
    //搜尋byID  
    let productID = sessionStorage.getItem("prodID")
    console.log(productID)
    let prodserchname = 'http://localhost:8080/TGA103_EagleMuseum/ProdgetById'
    fetch('http://localhost:8080/TGA103eagleMuseum/ProductGetName', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
          productID
        })
      })
      .then(resp => resp.json())
      .then(Data => {
        console.log(Data)
      })
    

