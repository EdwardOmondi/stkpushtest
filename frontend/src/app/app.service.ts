import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AppService {
  requestError = new BehaviorSubject('');

  constructor(private http: HttpClient) {}

  submitForm(body: any): Observable<StkPushResponse> {
    const url = environment.backendUrl.base + environment.backendUrl.stkpushApi;
    return this.http
      .post<StkPushResponse>(url, body)
      .pipe(catchError(this.handleError));
  }
  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      console.error('An error occurred:', error.error);
      this.requestError.next(`An error occurred: ${error.error}`);
    } else {
      console.error(
        `Backend returned code ${error.status}, body was: `,
        error.error
      );
      this.requestError.next(
        `Backend returned code ${error.status}, body was: ${error.error}`
      );
    }
    return throwError(() => {
      this.requestError.next(`Something bad happened; please try again later.`);
      return new Error('Something bad happened; please try again later.');
    });
  }
}
export interface StkPushResponse {
  MerchantRequestID: string;
  CheckoutRequestID: string;
  ResponseCode: string;
  ResponseDescription: string;
  CustomerMessage: string;
  requestId: string;
  errorCode: string;
  errorMessage: string;
}
