export interface Contact {
  name: string;
  mobile: string;
  email: string;
}

export interface ResponseDetails {
  code: number;
  message?: String;
  data: Contact[] | String;
}
