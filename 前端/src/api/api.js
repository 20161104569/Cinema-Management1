import Axios from "axios";

  export function submitManuscript(query) {
    return Axios.request({
      url: 'http://localhost:8080/maven-ssm-web/personController/login',
      method: 'post',
      params: query
    })
  }

  export function queryPerson(query) {
    return Axios.request({
      url: 'http://localhost:8080/maven-ssm-web/personController/allPerson',
      method: 'get',
      params: query
    })
  }

  export function queryPersonForType(query) {
    return Axios.request({
      url: 'http://localhost:8080/maven-ssm-web/personController/queryPersonForType',
      method: 'get',
      params: query
    })
  }

  export function addObj(query) {
    return Axios.request({
      url: 'http://localhost:8080/maven-ssm-web/personController/addPerson',
      method: 'post',
      params: query
    })
  }

  export function queryPersonForName(query) {
    return Axios.request({
      url: 'http://localhost:8080/maven-ssm-web/personController/queryPersonForName',
      method: 'get',
      params: query
    })
  }

  export function delObj(query) {
    return Axios.request({
      url: 'http://localhost:8080/maven-ssm-web/personController/delPerson',
      method: 'delete',
      params: query
    })
  }

  export function putObj(query) {
    return Axios.request({
      url: 'http://localhost:8080/maven-ssm-web/personController/updatePerson',
      method: 'get',
      params: query
    })
  }

  export function queryMovie(query) {
    return Axios.request({
      url: 'http://localhost:8080/maven-ssm-web/movieInfoController/allMovieInfo',
      method: 'get',
      params: query
    })
  }

  export function queryManagerMovie(query) {
    return Axios.request({
      url: 'http://localhost:8080/maven-ssm-web/movieInfoController/allManagerMovieInfo',
      method: 'get',
      params: query
    })
  }

  export function queryMoiveForName(query) {
    return Axios.request({
      url: 'http://localhost:8080/maven-ssm-web/movieInfoController/queryMoiveForName',
      method: 'get',
      params: query
    })
  }

  export function addMovieObj(query) {
    return Axios.request({
      url: 'http://localhost:8080/maven-ssm-web/movieInfoController/addMovieInfo',
      method: 'post',
      params: query
    })
  }

  export function putMovieObj(query) {
    return Axios.request({
      url: 'http://localhost:8080/maven-ssm-web/movieInfoController/updateMovieInfo',
      method: 'get',
      params: query
    })
  }

  export function delMovieObj(query) {
    return Axios.request({
      url: 'http://localhost:8080/maven-ssm-web/movieInfoController/delMovieInfo',
      method: 'delete',
      params: query
    })
  }

  export function buyTicket(query) {
    return Axios.request({
      url: 'http://localhost:8080/maven-ssm-web/TicketPurchaseInfoController/buyTicket',
      method: 'get',
      params: query
    })
  }

  export function delTicketObj(query) {
    return Axios.request({
      url: 'http://localhost:8080/maven-ssm-web/TicketPurchaseInfoController/delTicket',
      method: 'delete',
      params: query
    })
  }

  export function queryTicket(query) {
    return Axios.request({
      url: 'http://localhost:8080/maven-ssm-web/TicketPurchaseInfoController/allTicketInfo',
      method: 'get',
      params: query
    })
  }

  export function checkTicket(query) {
    return Axios.request({
      url: 'http://localhost:8080/maven-ssm-web/TicketPurchaseInfoController/checkTicket',
      method: 'get',
      params: query
    })
  }

  export function managerBuyTicket(query) {
    return Axios.request({
      url: 'http://localhost:8080/maven-ssm-web/TicketPurchaseInfoController/managerBuyTicket',
      method: 'get',
      params: query
    })
  }

  export function queryAllTicket(query) {
    return Axios.request({
      url: 'http://localhost:8080/maven-ssm-web/TicketPurchaseInfoController/queryAllTicket',
      method: 'get',
      params: query
    })
  }

  export function queryTicketForUserName(query) {
    return Axios.request({
      url: 'http://localhost:8080/maven-ssm-web/TicketPurchaseInfoController/queryTicketForUserName',
      method: 'get',
      params: query
    })
  }

  export function register(query) {
    return Axios.request({
      url: 'http://localhost:8080/maven-ssm-web/personController/register',
      method: 'get',
      params: query
    })
  }

  export function queryPersonById(query) {
    return Axios.request({
      url: 'http://localhost:8080/maven-ssm-web/personController/queryPersonById',
      method: 'get',
      params: query
    })
  }
