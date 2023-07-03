

const box= document.querySelector(".box")
const leftArror = document.querySelector(".left-arror")
const rightArror = document.querySelector(".right-arror")
const allitem = document.querySelectorAll(".item")
const dots =document.querySelector(".dots")




const classArr = ["one","two","three","four","five","six"]
let currentDotIndex = 0
let allDot =null

const handleRight =() =>{
    classArr.unshift(classArr[allitem.length-1])
    classArr.pop()
    for(let i=0;i<=allitem.length-1;i++){
        allitem[i].className=`item ${classArr[i]}`
        if(allitem[i].className.includes("one")){
            allitem[i].classList.add("active")
        }
    }

    currentDotIndex++
    if(currentDotIndex >= allDot.length){
        currentDotIndex=0
    }
    document.querySelector(".show").classList.remove("show")
    allDot[currentDotIndex].classList.add("show")
}

const handleLeft =() =>{

    classArr.push(classArr[0])
    classArr.shift()

    for(let i=0;i<=allitem.length-1;i++){
        allitem[i].className=`item ${classArr[i]}`
        if(allitem[i].className.includes("one")){
            allitem[i].classList.add("active")
        }
    }
    currentDotIndex--
    if(currentDotIndex<=-1){
        currentDotIndex =allDot.length -1
    }{
        document.querySelector(".show").classList.remove("show")
        allDot[currentDotIndex].classList.add("show")
    }
}

rightArror.addEventListener("click",handleRight)

leftArror.addEventListener("click",handleLeft)

const handleClick = e =>{
    const currentClickIndex = +e.target.getAttribute("data-index")
    document.querySelector(".show").classList.remove("show")
    allDot[currentClickIndex].classList.add("show")
    const centerElement = classArr.indexOf("one")
    let dif =Math.max(centerElement,currentClickIndex) - Math.min(centerElement,currentClickIndex)

    if(currentClickIndex > centerElement){
        while(dif--){
            handleRight()
        }
    }else{
        while(dif--){
            handleLeft()
        }
    }
}

const autoCreateDot = () =>{
    for(let i=0; i <= allitem.length -1 ;i++){
        const li =document.createElement("li")
        li.setAttribute("class","dot")
        li.setAttribute("data-index",`${i}`)
        li.addEventListener("click",handleClick)
        dots.appendChild(li)
    }
    allDot = document.querySelectorAll(".dot")

    allDot[0].classList.add("show")
}

autoCreateDot()

let timer = null

timer = setInterval(() => {
    rightArror.click()
},3000)
box.addEventListener("mouseenter",()=>{
    clearInterval(timer)
})
box.addEventListener("mouseleave",()=>{
    timer =setInterval(()=>{
        rightArror.click()
    },3000)
})