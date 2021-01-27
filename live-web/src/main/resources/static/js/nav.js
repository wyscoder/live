// /*
//     导航的js特效
//  */
// window.onload = function () {
//
//
//     var lists = document.getElementsByClassName("nav nav-pills")[0].getElementsByTagName("li");
//     for(var i=0;i<lists.length;i++){
//         lists[i].onclick = function () {
//             for(var i=0;i<lists.length;i++){
//                 if(this.contains(lists[i])){
//                     lists[i].classList.add("active");
//                 }else{
//                     lists[i].classList.remove("active");
//                 }
//             }
//         }
//     }
// }