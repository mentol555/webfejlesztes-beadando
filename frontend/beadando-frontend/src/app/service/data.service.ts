import { Cars } from './../cars';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  baseUrl = 'http://localhost:8080/api/cars'
  constructor(private http: HttpClient) { }

  // get all cars
  getCars() {
    return this.http.get(this.baseUrl)
  }

  getCarById(id: string) {
    return this.http.get(this.baseUrl + '/' + id)
  }

  addCar(newcar: any) {
    return this.http.post(this.baseUrl, newcar)
  }

  deleteCar(id: string) {
    return this.http.delete(this.baseUrl + '/' + id)
  }

  updateCar(car: any, id: string) {
    return this.http.put(this.baseUrl + '/' + id, car)
  }

  updateCarName(newname: string, id: string) {
    return this.http.patch(this.baseUrl + '/' + id, newname)
  }
}
