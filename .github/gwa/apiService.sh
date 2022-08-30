# used for initialize the service.yaml file, update the parameters based on your service and your application

export NS="nr-d2723f"
export NAME="nrfc"
export ZONE="test"
export SERVICE="nrfc-test-backend"
export NAMESPACE="d2723f-test"

echo "
services:
- name: $NAME-$ZONE
  host: $SERVICE.$NAMESPACE.svc
  tags: [ ns.$NS.$ZONE ]
  port: 80
  protocol: http
  retries: 0
  routes:
  - name: $NAME-$ZONE-route
    tags: [ ns.$NS.$ZONE ]
    hosts:
    - $NAME-$ZONE.api.gov.bc.ca
    paths:
    - /
    methods:
    - GET
    - OPTIONS
    strip_path: false
    https_redirect_status_code: 426
    path_handling: v0
" > service.yaml