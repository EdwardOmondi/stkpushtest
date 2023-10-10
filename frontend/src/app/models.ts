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
export interface Pageable {
  content: Content[]
  pageable: Pageable
  totalPages: number
  totalElements: number
  last: boolean
  size: number
  number: number
  sort: Sort
  numberOfElements: number
  first: boolean
  empty: boolean
}

export interface Content {
  createdDate: string
  merchantRequestId: string
  checkoutRequestId: string
  resultCode: number
  resultDesc: string
  amount: number
  mpesaReceiptNumber: string
  transactionDate: string
  phoneNumber: number
  id: number
  ref: string
}

export interface Pageable {
  pageNumber: number
  pageSize: number
  sort: Sort
  offset: number
  paged: boolean
  unpaged: boolean
}

export interface Sort {
  empty: boolean
  unsorted: boolean
  sorted: boolean
}
