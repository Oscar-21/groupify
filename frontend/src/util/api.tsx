export function apiGet<T>(url: string): Promise<T> {
  return fetch(url, {
    headers: { Authorization: `Bearer ${localStorage.getItem("token")}` },
  })
    .then(response => {
      if (!response.ok) {
        throw new Error(response.statusText)
      }
      return response.json().then(data => data as T);
    })
};

export function apiPost<T>(url: string, data: FormData): Promise<T> {
  return fetch(url, {
    headers: { 
      Authorization: `Bearer ${localStorage.getItem("token")}` 
    },
  method: 'POST',
  body: data
  })
    .then(response => {
      if (!response.ok) {
        throw new Error(response.statusText)
      }
      return response.json().then(data => data as T);
    })
};

