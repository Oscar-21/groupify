export default async (token: String) => {
  let authorized;
  try {
    const response = await fetch(`/api/v1/authorize`, {
      headers: { Authorization: `Bearer ${token}` },
    });
    authorized = await response.json();
  } catch (err) {
    return {
      error: err,
    }
  }
  return authorized;
};