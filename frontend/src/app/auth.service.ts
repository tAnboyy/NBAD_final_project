import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(credentials: {username: string, password: string}): Observable<any> {
    return this.http.post('/auth/login', credentials);
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }
}
